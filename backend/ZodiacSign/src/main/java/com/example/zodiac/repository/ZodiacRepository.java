package com.example.zodiac.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.zodiac.entity.Zodiac;

public interface ZodiacRepository extends JpaRepository<Zodiac, Integer>{

	Zodiac save(LocalDate birthDate);

}
