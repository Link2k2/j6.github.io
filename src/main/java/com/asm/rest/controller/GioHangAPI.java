package com.asm.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.asm.dao.GioHangDAO;
import com.asm.dao.NguoiDungDAO;
import com.asm.entity.GioHang;
import com.asm.entity.NguoiDung;
import com.asm.service.GioHangService;

@CrossOrigin("*")
@RestController
public class GioHangAPI {
	
	@Autowired
	HttpServletRequest request;

	
	@Autowired
	GioHangDAO dao;

	@Autowired
	GioHangService gioHangService;

	@GetMapping("/rest/giohang/list")
	public ResponseEntity<List<GioHang>> findGioHangbyNguoiDung() {
		return ResponseEntity.ok(gioHangService.getItems(request.getRemoteUser()));

	}
	

	@PostMapping("/rest/giohang/create/{idsp}") //idsp: id sản phẩm
	public ResponseEntity<Void> create(
			@PathVariable("idsp") Optional<String> idsp) {
		if(idsp.isPresent()) {
			gioHangService.add(idsp.get(), request.getRemoteUser());//sử dụng hàm add trong giỏ hàng service đã viết
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/rest/giohang/update/{id}/{sl}") // id này là id của đối tượng trong giỏ
	public ResponseEntity<Void> update(@PathVariable("id") Optional<Integer> id,
			@PathVariable("sl") Optional<Integer> sl) {//sl là số lượng
		gioHangService.update(id.get(), sl.get());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/rest/giohang/remove/{id}")//id này là id của đối tượng trong giỏ luôn
	public ResponseEntity<Void> delete(@PathVariable("id") Optional<Integer> id) {
		gioHangService.remove(id.get());//sử dụng hàm remove trong giỏ hàng service đã viết
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/rest/giohang/clear/{id}")//id này là id của người dùng :)) xin lỗi vì sự bất tiện trong cách đặt biến
	public ResponseEntity<Void> clear(@PathVariable("id") Optional<String> id) {
		gioHangService.removebByNguoiDung(id.get()); //sử dụng hàm removebyNguoiDung trong giỏ hàng service đã viết
		return ResponseEntity.ok().build();
	}
}
