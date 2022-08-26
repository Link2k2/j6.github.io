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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.NguoiDungDAO;
import com.asm.entity.NguoiDung;
import com.asm.service.NguoiDungService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nguoidung")
public class NguoiDungAPI {

	@Autowired
	NguoiDungService ndService;

	@GetMapping
	public List<NguoiDung> findAllNguoiDung(@RequestParam("admin") Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return ndService.getAdmins();
		}
		return ndService.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<NguoiDung> findNguoiDungById(@PathVariable("id") String id) {
		Optional<NguoiDung> student = ndService.findById(id);
		if (!student.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student.get());
	}

	@PostMapping
	public ResponseEntity<NguoiDung> Create(@RequestBody NguoiDung user) {
		if (ndService.existsById(user.getId())) {
			return ResponseEntity.badRequest().build();
		}
		ndService.saveAndFlush(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping("{id}")
	public ResponseEntity<NguoiDung> Update(@PathVariable("id") String id, @RequestBody NguoiDung user) {
		if (!ndService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		ndService.saveAndFlush(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		if (!ndService.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		ndService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
