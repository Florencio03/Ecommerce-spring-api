# **E-commerce backend**

This repository contains the complete source code for an e-commerce backend application built using Spring Boot.

---

## **Description**

This project is a Spring Boot–based backend for an e-commerce application that exposes RESTful APIs to access core business functionalities such as product management, order processing, and checkout.

### Architecture Overview
The application follows a layered architecture to ensure separation of concerns and maintainability:
- Controller Layer:
Exposes RESTful endpoints and handles HTTP requests and responses.

- Service Layer:
Contains business logic such as cart management, checkout flow, and order processing.

- Repository Layer:
Uses Spring Data JPA to interact with the database.

- Security Layer:
Implements JWT-based authentication and authorization using Spring Security.

- Integration Layer:
Handles external services such as Stripe payments and webhook events.

---

## **Getting Started**

### 1. Clone the Repository

```bash
git clone https://github.com/Florencio03/Ecommerce-spring-api.git

```

---

### 2. Configue Enviroment Variables 

- Rename the ``.env.example`` file to ``.env``. 
- Update the following environment variables inside .env: 

#### JWT_SECRET

Generate a secure random key using:

```bash
openssl rand -base64 32
```

If ``openssl`` is not available, go to [generate-random.org](https://generate-random.org), click on **Strings > API Tokens**, and generate a secure token.

#### STRIPE_SECRET_KEY

- Create a free account at [stripe.com](https://stripe.com)
- On your dashboard, go to **Developers > API Keys**.
- Copy the value of the **Secret Key**.

#### STRIPE_WEBHOOK_SECRET_KEY

- Install the Stripe CLI: https://docs.stripe.com/stripe-cli
- Login and start the webhook listener:

```bash
stripe login
stripe listen --forward-to http://localhost:8080/checkout/webhook
```
- Copy the **signing secret** from the terminal output and use it as the value for ``STRIPE_WEBHOOK_SECRET_KEY``.

---

## **Running the Project**

This is a Maven project. To start the application, run:

```bash
./mvnw spring-boot:run
```

If you're on Windows:

```bash
mvnw.cmd spring-boot:run
```

Once running, the application will be available at:

```bash
http://localhost:8080
```

---

## **API Documentation**

This proyect uses Swagger UI for API Documentation.

```bash
http://localhost:8080/swagger-ui.html
```

---

## **Example API Flow**

Below is a sample flow demonstrating how to interact with the API after starting the application.

### 1. Get All Products 

```bash
GET /products
```

The database is automatically populated with 10 sample products using a Flyway migration script.

### 2. Create a Shopping Cart 

```bash
POST /carts
```

This returns a cart ID. Authentication is not required to create a cart.

### 3. Add Items to Cart 

Once you have a cart ID, you can add products to it by sending:

```bash
POST /carts/{cartId}/items
```

**Request body example**:
```json
{
  "productId": 1
}
```

### 4. Register a New User 
To proceed with checkout, you must register and log in:

```bash
POST /users
```

**Request body**:

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "123456"
}
```

### 5. Login to Get an Access Token 

```bash
POST /auth/login 
```

**Request body**:
```json
{
  "email": "john@example.com",
  "password": "123456"
}
```

**Response body**:
```json
{
  "token": "your-json-web-token"
}
```

### 6. Checkout 

```bash
POST /checkout 
```

**Headers**
```bash
Authorization: Bearer your-json-web-token
```

**Request body**
```json
{
  "cartId": "your-cart-id"
}
```

This endpoint returns a Stripe checkout URL. Open it in your browser to complete the payment using a test card:

```yaml
Card: 4242 4242 4242 4242
Expiry: Any future date
CVC: Any 3 digits
```

### 7. Webhook & Order Status Update

Once payment is completed, Stripe will trigger a webhook call to:

```bash
POST /checkout/webhook 
```

The backend listens for this event and updates the order status in the database accordingly.

---
