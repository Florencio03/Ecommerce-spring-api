package com.example.Ecommerce.repositories;

import com.example.Ecommerce.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
