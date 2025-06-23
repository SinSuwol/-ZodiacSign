package com.example.zodiac.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.zodiac.entity.Zodiac;
import com.example.zodiac.service.ZodiacService;

@Controller
public class ZodiacController {
	
	@Autowired
	private ZodiacService zodiacService;
	
	@GetMapping("/")
	public String root() {
		return "zodiacForm";
	}
	
	@PostMapping("/regZodiac")
	public String regZodiac(@RequestParam("birthDate")LocalDate birthDate, Model model) {
		Zodiac zodiac = zodiacService.createZodiacInfo(birthDate);
		
		model.addAttribute("zodiac", zodiac);
		
		return "result";
	}

}
