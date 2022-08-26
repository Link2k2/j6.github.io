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

import com.asm.dao.UsersRoleDAO;
import com.asm.entity.UsersRole;
import com.asm.service.UsersRoleService;
import com.asm.service.impl.UserServiceImplement;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class UsersRoleAPI {

	@Autowired
	UsersRoleDAO dao;
	@Autowired UsersRoleService service;

	@GetMapping()
	public List<UsersRole> findAllUsersRole(@RequestParam("admin") Optional<Boolean> admin) {
		if(admin.orElse(false)) {
			return service.findAuthoritiesOfAdmin();
		}
		return service.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<UsersRole> findUsersRoleById(@PathVariable("id") Optional<Integer> id) {
		if(id.isPresent()) {
			Optional<UsersRole> student = service.findById(id.get());
			if (!student.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(student.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping()
	public UsersRole Create(@RequestBody UsersRole user) {
		return service.create(user);
	}

	@PutMapping("{id}")
	public ResponseEntity<UsersRole> Update(@PathVariable("id") Integer id, @RequestBody UsersRole user) {
		if (!service.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		service.saveAndFlush(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		if (!service.existsById(id)) {
			return ResponseEntity.badRequest().build();
		}
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
