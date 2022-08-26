package com.asm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.asm.entity.SanPham;

public interface SanPhamService {
	SanPham create(SanPham sp);

	SanPham update(SanPham sp);

	void delete(String id);
	
	List<SanPham> findAll();
	
	Page<SanPham> findAllPage(Pageable pageable);
	
	List<SanPham> findByCategoryId(Integer cate_id);
	Page<SanPham> findByCategoryId(Integer cate_id, Pageable page);
	
	SanPham findById(String id);
	
	Page<SanPham> findAllByName(String nameSP, Pageable page);
	Boolean existsById(String id);

}
