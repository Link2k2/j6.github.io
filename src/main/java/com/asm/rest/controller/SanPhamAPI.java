package com.asm.rest.controller;

import java.util.List;
import java.util.Optional;

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

import com.asm.dao.SanPhamDAO;
import com.asm.entity.SanPham;
import com.asm.service.SanPhamService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/sanpham")
public class SanPhamAPI {

	@Autowired
	SanPhamDAO dao;
	@Autowired SanPhamService spsSevice;

	@GetMapping
	public ResponseEntity<List<SanPham>> findAllSanPham() {
		return ResponseEntity.ok(spsSevice.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<SanPham> findSanPhamById(@PathVariable("id") Optional<String> id) {
		if (!spsSevice.existsById(id.get())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(spsSevice.findById(id.get()));
	}

	@PostMapping
	public ResponseEntity<SanPham> create(@RequestBody Optional<SanPham> sp) {
		if(sp.isPresent()) {
			spsSevice.create(sp.get());
		}
		
		return  ResponseEntity.ok().build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Optional<String> id
			,@RequestBody SanPham sp) {//sl là số lượng
		spsSevice.update(sp);
		return ResponseEntity.ok().build();
	}
	

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		spsSevice.delete(id);;
	}

}
