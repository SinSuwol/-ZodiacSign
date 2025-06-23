package com.example.zodiac.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.zodiac.entity.Zodiac;
import com.example.zodiac.service.ZodiacService;

@Controller
public class ZodiacController {

    @Autowired
    private ZodiacService zodiacService;

    private static final String ADMIN_PASSWORD = "1234";

    // 사용자: 생년월일 입력 → 별자리 결과
    @GetMapping("/")
    public String root() {
        return "zodiacForm";
    }

    @PostMapping("/regZodiac")
    public String regZodiac(@RequestParam("birthDate") LocalDate birthDate, Model model) {
        Zodiac zodiac = zodiacService.createZodiacInfo(birthDate);
        model.addAttribute("zodiac", zodiac);
        return "result";
    }

    // 관리자: 히든 클릭으로 접근하는 페이지 (비밀번호 입력 화면)
    @GetMapping("/auth")
    public String showAdminLoginForm() {
        return "adminAuth"; // 비밀번호 입력 페이지
    }

    // 관리자 인증
    @PostMapping("/auth")
    public String adminAuth(@RequestParam("adminPw") String adminPw) {
        if (ADMIN_PASSWORD.equals(adminPw)) {
            return "redirect:/admin/zodiacs";
        }
        return "redirect:/auth?error";
    }

    // 관리자: 전체 목록 보기
    @GetMapping("/admin/zodiacs")
    public String showAdminZodiacList(Model model) {
        List<Zodiac> zodiacs = zodiacService.findAll();
        model.addAttribute("zodiacs", zodiacs);
        return "zodiacAdmin";
    }

    // 관리자: 별자리 등록 폼 (선택사항)
    @GetMapping("/admin/zodiac/new")
    public String newZodiacForm(Model model) {
        model.addAttribute("zodiac", new Zodiac());
        return "zodiacEdit";
    }

    
    @PostMapping("/zodiac/update")
    public String updateZodiac(
        @RequestParam Integer id,
        @RequestParam String name,
        @RequestParam String birthDate,
        @RequestParam String imageUrl,
        @RequestParam String description) {

        Zodiac zodiac = new Zodiac();
        zodiac.setId(id);
        zodiac.setName(name);
        zodiac.setBirthDate(LocalDate.parse(birthDate));
        zodiac.setImageUrl(imageUrl);
        zodiac.setDescription(description);

        zodiacService.update(zodiac);
        return "redirect:/zodiac/list";
    }

    // 관리자: 별자리 저장
    @PostMapping("/zodiac/save")
    public String saveZodiac(@ModelAttribute Zodiac zodiac) {
        zodiacService.save(zodiac);
        return "redirect:/admin/zodiacs";
    }

    // 관리자: 별자리 수정 폼
    @GetMapping("/zodiac/edit")
    public String editPage(@RequestParam("id") Integer id, Model model) {
        Zodiac zodiac = zodiacService.findById(id);
        model.addAttribute("zodiac", zodiac);
        return "zodiacEdit";
    }

    @GetMapping("/zodiac/edit/{id}")
    public String editZodiac(@PathVariable("id") Integer id, Model model) {
        Zodiac zodiac = zodiacService.findById(id);
        model.addAttribute("zodiac", zodiac);
        return "zodiacEdit";  // templates/zodiacEdit.html 로 렌더링
    }


    // 사용자용 목록 (옵션)
    @GetMapping("/zodiac/list")
    public String listZodiacs(Model model) {
        model.addAttribute("zodiacs", zodiacService.findAll());
        return "zodiacList";
    }
}
