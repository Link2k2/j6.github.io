package com.asm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.Galery;

public interface GaleryDAO extends JpaRepository<Galery, Integer> {
	@Query("SELECT g FROM Galery g WHERE g.sp.id = ?1")
	List<Galery> findByspId(String id); 
}
