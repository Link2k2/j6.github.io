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

import com.asm.dao.RoleDAO;
import com.asm.entity.Role;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleAPI {

	@Autowired
	RoleDAO dao;

	@GetMapping()
	public ResponseEntity<List<Role>> findAllRole() {
		return ResponseEntity.ok(dao.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<Role> findRoleById(@PathVariable("id") String id) {
		Optional<Role> student = dao.findById(id);
		if (!student.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(student.get());
	}

	@PostMapping()
	public ResponseEntity<Role> Create(@RequestBody Role user) {
		if (dao.existsById(user.getId())) {
			return ResponseEntity.badRequest().build();
		}
		dao.saveAndFlush(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping("{id}")
	public ResponseEntity<Role> Update(@PathVariable("id") String id,
			@RequestBody Role user) {
		if (!dao.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dao.saveAndFlush(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		if (!dao.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		dao.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
