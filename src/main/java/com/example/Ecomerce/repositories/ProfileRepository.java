package com.example.Ecomerce.repositories;

import com.example.Ecomerce.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
