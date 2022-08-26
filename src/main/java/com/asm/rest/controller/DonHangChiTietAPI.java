package com.asm.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.asm.dao.DonHangChiTietDAO;
import com.asm.dao.DonHangDAO;
import com.asm.entity.DonHang;
import com.asm.entity.DonHangChiTiet;

@CrossOrigin("*")
@RestController
public class DonHangChiTietAPI {
	@Autowired
	DonHangChiTietDAO dao;

	@Autowired
	DonHangDAO dhdao;

	@GetMapping("/rest/donhangct/{iddh}")
	public ResponseEntity<List<DonHangChiTiet>> findGaleryById(@PathVariable("iddh") Optional<String> iddh) {
		DonHang dh = dhdao.findById(iddh.get()).get();
		return ResponseEntity.ok(dao.findByDonHang(dh));
	}

}
