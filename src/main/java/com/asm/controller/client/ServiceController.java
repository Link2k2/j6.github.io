package com.asm.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceController {
	@RequestMapping("/home/feedback")
	public String feedback() {
		return "service/feedback";
	}
	
	@RequestMapping("/home/contact")
	public String contact() {
		return "service/contact";
	}
	
	@RequestMapping("/home/introduce")
	public String introduce() {
		return "service/introduce";
	}
	
	@RequestMapping("/home/policy")
	public String policy() {
		return "service/policy";
	}
	
	@RequestMapping("/home/mfeedback")
	public String mfeedback() {
		return "service/mfeedback";
	}
}
