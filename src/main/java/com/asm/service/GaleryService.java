package com.asm.service;

import java.util.List;

import com.asm.entity.Galery;

public interface GaleryService {

	
	List<Galery> findAll();
	
	Galery findById(Integer id);
	
	List<Galery> findBySpId(String spId);
	
	
}
