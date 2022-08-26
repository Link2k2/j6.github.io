package com.asm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, String> {
	
	@Query(value="SELECT p FROM SanPham p WHERE p.loaisp.id = ?1")
	List<SanPham> findByCategoryId(int cate_id);
	
	@Query(value="SELECT p FROM SanPham p WHERE p.loaisp.id = ?1")
	Page<SanPham> findByCategoryId(int cate_id, Pageable page);
	
	@Query(value="SELECT p FROM SanPham p WHERE p.tenSP LIKE ?1")
	Page<SanPham> findAllByName(String nameSP, Pageable page);
}
