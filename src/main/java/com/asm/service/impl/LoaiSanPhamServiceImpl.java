package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.LoaiSanPhamDAO;
import com.asm.entity.LoaiSanPham;
import com.asm.service.LoaiSanPhamService;
@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService{
	
	@Autowired
	LoaiSanPhamDAO loaiSPDao;
	
	@Override
	public List<LoaiSanPham> findAll() {
		// TODO Auto-generated method stub
		return loaiSPDao.findAll();
	}

	

	@Override
	public LoaiSanPham findById(Integer id) {
		// TODO Auto-generated method stub
		return loaiSPDao.findById(id).get();
	}

	

}
