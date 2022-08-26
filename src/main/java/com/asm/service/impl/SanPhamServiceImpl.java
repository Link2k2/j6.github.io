package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asm.dao.SanPhamDAO;
import com.asm.entity.SanPham;
import com.asm.service.SanPhamService;
@Service
public class SanPhamServiceImpl implements SanPhamService {
	@Autowired SanPhamDAO dao;
	
	@Override
	public SanPham create(SanPham sp) {
		// TODO Auto-generated method stub
		return dao.save(sp);
	}

	@Override
	public SanPham update(SanPham sp) {
		// TODO Auto-generated method stub
		return dao.saveAndFlush(sp);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dao.deleteById(id);;
	}

	@Override
	public List<SanPham> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<SanPham> findByCategoryId(Integer cate_id) {
		// TODO Auto-generated method stub
		return dao.findByCategoryId(cate_id);
	}

	@Override
	public SanPham findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public Page<SanPham> findAllPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findAll(pageable);
	}

	@Override
	public Page<SanPham> findAllByName(String nameSP, Pageable page) {
		// TODO Auto-generated method stub
		return dao.findAllByName(nameSP, page);
	}

	@Override
	public Page<SanPham> findByCategoryId(Integer cate_id, Pageable page) {
		// TODO Auto-generated method stub
		return dao.findByCategoryId(cate_id, page);
	}

	@Override
	public Boolean existsById(String id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}
	
	
}
