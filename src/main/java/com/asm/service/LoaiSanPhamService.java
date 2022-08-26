package com.asm.service;

import java.util.List;

import com.asm.entity.LoaiSanPham;


public interface LoaiSanPhamService {
	List<LoaiSanPham> findAll();
	
	
	LoaiSanPham findById(Integer id);
}
