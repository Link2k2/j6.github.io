package com.asm.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FavoriteController {
	@RequestMapping("/home/account/favorite")
	public String view() {
		return "product/myfavorite";
	}
}
