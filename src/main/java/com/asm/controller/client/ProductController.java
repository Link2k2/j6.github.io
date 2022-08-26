package com.asm.controller.client;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.dao.SanPhamDAO;
import com.asm.entity.Galery;
import com.asm.entity.LoaiSanPham;
import com.asm.entity.SanPham;
import com.asm.service.GaleryService;
import com.asm.service.LoaiSanPhamService;
import com.asm.service.SanPhamService;
import com.asm.service.impl.SanPhamServiceImpl;



@Controller
public class ProductController {
	
	@Autowired
	SanPhamService sanphamService;
	
	@Autowired
	LoaiSanPhamService loaiSPService;
	
	@Autowired
	GaleryService galeryService;
	
	@RequestMapping("/home/product")
	public String viewProduct(Model model, @RequestParam("idLoai") Optional<Integer>idL) {
			
		if(idL.isPresent()) {
			Pageable pageable = PageRequest.of(0, 10);
			Page<SanPham> list = sanphamService.findByCategoryId(idL.get(), pageable);

			model.addAttribute("page1", list);
		}else {
			Pageable pageable = PageRequest.of(0, 10);
			///
			Page<SanPham> page = sanphamService.findAllPage(pageable); 
			model.addAttribute("page1", page);
		}
		
		return "product/list";
	}
	
	@PostMapping("/home/product/search")
	public String searchProduct(Model model, @RequestParam("search") Optional<String> nameProduct) {
		
		if(nameProduct.isPresent()) {
			Pageable pageable = PageRequest.of(0, 10);
			String product = nameProduct.get();
			Page<SanPham> list = sanphamService.findAllByName(product, pageable);

			if(list.isEmpty()) {

				model.addAttribute("messageError", "Sản phẩm không có trong cửa hàng");
			}else {
				
				model.addAttribute("page1", list);
			}
		}
		else {
			model.addAttribute("messageError", "Không để trống tên sản phẩm");

		}
		
		return "product/list";
	}
	
	@RequestMapping("/home/product/detail")
	public String viewProductDetail(Model model, @RequestParam("id") Optional<String> id ) {
		
		if(id.isPresent()) {
			
			String idProduct = id.get();
			SanPham sp = sanphamService.findById(idProduct);
			List<Galery> list = galeryService.findBySpId(idProduct); 
			model.addAttribute("galeryList", list);
			model.addAttribute("sanPham", sp);
			
			}
		else {
			model.addAttribute("messageError", "Không để trống tên sản phẩm");

		}
		return "product/detail";
	}
	
	//Page
	
	@RequestMapping(path = {"/home/product/page","/home/index/page"})
	public String page(Model model, @RequestParam("p") Optional<Integer>pageNum) {
		try {
			//paginate
			
			Pageable pageable = PageRequest.of(pageNum.orElse(0), 10);
			

			///
			Page<SanPham> page = sanphamService.findAllPage(pageable); 
			model.addAttribute("page1", page);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			
			return "product/list";
	}
}
