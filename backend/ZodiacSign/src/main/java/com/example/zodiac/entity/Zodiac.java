package com.example.zodiac.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zodiac")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zodiac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @Column(length = 1000)
	private String name;
    @Column(length = 2000)
	private LocalDate birthDate;
    @Column(length = 5000)
    private String description;
    @Column(length = 20000)
	private String imageUrl;
}
