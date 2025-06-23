package com.example.zodiac.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zodiac.entity.Zodiac;
import com.example.zodiac.repository.ZodiacRepository;

@Service
public class ZodiacService {
	
	@Autowired
	private ZodiacRepository zodiacRepository;

	public String getZodiacName(LocalDate date) {
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
		
		if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "물병자리";
		if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return "물고기자리";
		if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "양자리";
		if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "황소자리";
		if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) return "쌍둥이자리";
		if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) return "게자리";
		if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "사자자리";
		if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "처녀자리";
		if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "천칭자리";
		if ((month == 10 && day >= 23) || (month == 11 && day <= 22)) return "전갈자리";
		if ((month == 11 && day >= 23) || (month == 12 && day <= 24)) return "사수자리";
		return "염소자리";
	}

	// ✅ DB에서 name으로 불러오기
	public Zodiac createZodiacInfo(LocalDate birthDate) {
		String name = getZodiacName(birthDate);

		// DB에서 image_url까지 포함된 전체 Zodiac 조회
		return zodiacRepository.findByName(name)
			.orElseGet(() -> {
				Zodiac z = new Zodiac();
				z.setName(name);
				z.setBirthDate(birthDate);
				z.setImageUrl("https://example.com/default.png"); // 기본 이미지
				return z;
			});
	}

	public void save(Zodiac zodiac) {
		zodiacRepository.save(zodiac);
	}
	
	public List<Zodiac> findAll() {
	    return zodiacRepository.findAll();
	}

	public Zodiac findById(Integer id) {
	    return zodiacRepository.findById(id).orElse(null);
	}

	public void update(Zodiac zodiac) {
	    // 기존 객체 수정 후 저장
	    Zodiac existing = zodiacRepository.findById(zodiac.getId()).orElse(null);
	    if (existing != null) {
	        existing.setName(zodiac.getName());
	        existing.setBirthDate(zodiac.getBirthDate());
	        existing.setImageUrl(zodiac.getImageUrl());
	        existing.setDescription(zodiac.getDescription());
	        zodiacRepository.save(existing);
	    }
	}

}
