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

import com.asm.dao.GaleryDAO;
import com.asm.entity.Galery;

@CrossOrigin("*")
@RestController
public class GaleryAPI {

	@Autowired
	GaleryDAO dao;

	@GetMapping("/rest/galery")
	public ResponseEntity<List<Galery>> findAllGalery() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("/rest/galery/{id}")
	public ResponseEntity<Galery> findGaleryById(@PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(id.get())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id.get()).get());
	}

	@PostMapping("rest/galery")
	public ResponseEntity<Galery> create(@RequestBody Optional<Galery> ga) {
		dao.saveAndFlush(ga.get());
		return ResponseEntity.ok().build();
	}

	@PutMapping("rest/galery/{id}")
	public ResponseEntity<Galery> update(@RequestBody Optional<Galery> ga, @PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(ga.get().getId())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.saveAndFlush(ga.get()));
	}

	@DeleteMapping("rest/galery/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(id.orElse(0))) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id.orElse(0));
		return ResponseEntity.ok().build();
	}

}
