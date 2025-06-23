package com.example.zodiac.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zodiac.entity.Zodiac;

public interface ZodiacRepository extends JpaRepository<Zodiac, Integer>{
	Optional<Zodiac> findByName(String name);
}
