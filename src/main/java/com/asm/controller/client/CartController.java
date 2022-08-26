package com.asm.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/home/account/cart")
	public String view() {
		
		
		return "cart/view";
	}
	
	@RequestMapping("/home/account/cart-detail")
	public String viewCartDetail() {
		return "cart/cartdetail";
	}
	
	@RequestMapping("/home/account/finish")
	public String finish() {
		return "cart/finish";
	}
}
