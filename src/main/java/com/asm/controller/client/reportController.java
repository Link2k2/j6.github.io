package com.asm.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class reportController {
	@RequestMapping("/admin/report")
	public String reportController1() {
		// TODO Auto-generated constructor stub
		return "account/change";
	}

}
