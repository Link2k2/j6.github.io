package com.asm.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.LoaiSanPhamDAO;
import com.asm.entity.LoaiSanPham;

@CrossOrigin("*")
@RestController
public class LoaiSanPhamAPI {

	@Autowired
	LoaiSanPhamDAO dao;
	
	
	@GetMapping("/rest/loaisanpham")
	public ResponseEntity<List<LoaiSanPham>> findAllLoaiSP() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("/rest/loaisanpham/{id}")
	public ResponseEntity<LoaiSanPham> findLoaiSPById(@PathVariable("id") Integer id) {
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id).get());
	}

	@PostMapping("/rest/loaisanpham")
	public ResponseEntity<LoaiSanPham> create(@RequestBody LoaiSanPham sp) {
		dao.saveAndFlush(sp);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/rest/loaisanpham/{id}")
	public ResponseEntity<LoaiSanPham> update(@PathVariable("id") String id,
			@RequestBody LoaiSanPham sp) {
		return ResponseEntity.ok(dao.saveAndFlush(sp));
	}

	@DeleteMapping("/rest/loaisanpham/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		if(!dao.existsById(id)) {
			return ResponseEntity.badRequest().build(); 
		}
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}

}