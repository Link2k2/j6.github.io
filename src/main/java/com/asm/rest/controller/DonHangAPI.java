package com.asm.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.DonHangDAO;
import com.asm.dao.NguoiDungDAO;
import com.asm.entity.DonHang;
import com.asm.entity.NguoiDung;
import com.asm.service.DonHangService;
import com.asm.service.NguoiDungService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
public class DonHangAPI {
	@Autowired
	NguoiDungService ndService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	DonHangService donHangService;

	@GetMapping("/rest/donhang/listid")
	public ResponseEntity<List<DonHang>> findDonHangById() {
			NguoiDung nd = ndService.findById(request.getRemoteUser()).get();
			return ResponseEntity.ok(donHangService.findByNguoiDung(nd));
		
				
	}

	@GetMapping("/rest/donhang")
	public ResponseEntity<List<DonHang>> findAllDonHang() {
		return ResponseEntity.ok(donHangService.findAll());
	}

	@PostMapping("/rest/donhang/create")
	public DonHang create(@RequestBody JsonNode orderData) {
		return donHangService.create(orderData);//chỗ này là sử dụng hàm create trong service nè
	}
	
	@GetMapping("/rest/donhang-all")
	public List<DonHang> findAllDonHangHT() {
		return donHangService.findAllDH();
	}
	
	@GetMapping("/rest/donhang-huy")
	public List<DonHang> findAllDonHangHuy() {
		return donHangService.getDonDaHuy();
	}

	
	
	@PutMapping("/rest/donhang/{id}")
	public ResponseEntity<DonHang> Update(@PathVariable("id") String id, 
			@RequestBody DonHang user) {
		if (!donHangService.existsById(id)) {
			System.out.println("A");
			return ResponseEntity.notFound().build();
		}
		donHangService.saveAndFlush(user);
		return ResponseEntity.ok(user);
	}
}
