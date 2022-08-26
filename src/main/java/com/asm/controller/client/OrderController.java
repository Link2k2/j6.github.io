package com.asm.controller.client;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asm.entity.DonHang;
import com.asm.service.DonHangService;

@Controller
public class OrderController {
	
	@Autowired
	DonHangService donHangService;
	
	
	
	
	@RequestMapping("/home/account/order-detail/{id}")
	public String orderdetail(@PathVariable("id") Optional<String> id,Model md) {
		md.addAttribute("dh",donHangService.findById(id.get()));
		md.addAttribute("amount",donHangService.amount(id.get()));
		return "cart/bill";
	}
	
	@RequestMapping("/home/account/bill")
	public String bill() {
		return "cart/bill";
	}
	
	@RequestMapping("/home/account/history-order")
	public String historyOrder() {
		return "account/history";
	}
}
