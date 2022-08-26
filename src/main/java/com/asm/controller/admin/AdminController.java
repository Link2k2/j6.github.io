package com.asm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html"; //tạo url chuyển sang html
	}
}
