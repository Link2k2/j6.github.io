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
import org.springframework.web.bind.annotation.RestController;

import com.asm.dao.PhanHoiDAO;
import com.asm.entity.PhanHoi;

@CrossOrigin("*")
@RestController
public class PhanHoiAPI {
	@Autowired
	PhanHoiDAO dao;

	@GetMapping("J6/api/phanhoi")
	public ResponseEntity<List<PhanHoi>> findAllPhanHoi() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("J6/api/phanhoi/{id}")
	public ResponseEntity<PhanHoi> findPhanHoiById(@PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(id.orElse(0))) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id.get()).get());
	}

	@PostMapping("J6/api/phanhoi")
	public ResponseEntity<PhanHoi> create(@RequestBody Optional<PhanHoi> ph) {
		ph.get().setId(null);
		return ResponseEntity.ok(dao.saveAndFlush(ph.get()));
	}

	@PutMapping("J6/api/phanhoi")
	public ResponseEntity<PhanHoi> update(@RequestBody Optional<PhanHoi> ph) {
		if (!dao.existsById(ph.get().getId())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.saveAndFlush(ph.get()));
	}

	@DeleteMapping("J6/api/phanhoi/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(id.orElse(0))) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id.orElse(0));
		return ResponseEntity.ok().build();
	}

}
