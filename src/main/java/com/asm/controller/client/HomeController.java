package com.asm.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asm.entity.SanPham;
import com.asm.service.SanPhamService;

@Controller
public class HomeController {
	
	@Autowired
	SanPhamService sanphamService;
	@RequestMapping("/home/index")
	public String index(Model model) {
		
	
	Pageable pageable = PageRequest.of(0, 10);
	///
	Page<SanPham> page = sanphamService.findAllPage(pageable); 
	model.addAttribute("page1", page);
		return "home/index";
	}
}
