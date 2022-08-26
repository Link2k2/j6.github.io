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

import com.asm.dao.FavoriteDAO;
import com.asm.entity.Favorite;

@CrossOrigin("*")
@RestController
public class FavoriteAPI {

	@Autowired
	FavoriteDAO dao;

	@GetMapping("/rest/favorite")
	public ResponseEntity<List<Favorite>> findAllFavorite() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("/rest/favorite/{id}")
	public ResponseEntity<Favorite> findFavoriteById(@PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(id.get())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.findById(id.get()).get());
	}

	@PostMapping("/rest/favorite")
	public ResponseEntity<Favorite> create(@RequestBody Optional<Favorite> fa) {
		fa.get().setId(null);
		return ResponseEntity.ok(dao.saveAndFlush(fa.get()));
	}

	@PutMapping("/rest/favorite")
	public ResponseEntity<Favorite> update(@RequestBody Optional<Favorite> fa) {
		if (!dao.existsById(fa.get().getId())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dao.saveAndFlush(fa.get()));
	}

	@DeleteMapping("/rest/favorite/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Optional<Integer> id) {
		if (!dao.existsById(id.orElse(0))) {
			return ResponseEntity.notFound().build();
		}
		dao.deleteById(id.orElse(0));
		return ResponseEntity.ok().build();
	}
}
