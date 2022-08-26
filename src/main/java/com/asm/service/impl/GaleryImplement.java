package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.GaleryDAO;
import com.asm.entity.Galery;
import com.asm.service.GaleryService;

@Service
public class GaleryImplement implements GaleryService{

	@Autowired
	GaleryDAO galeryDao;
	
	@Override
	public List<Galery> findAll() {
		// TODO Auto-generated method stub
		return galeryDao.findAll();
	}

	
	@Override
	public Galery findById(Integer id) {
		// TODO Auto-generated method stub
		return galeryDao.findById(id).get();
	}


	@Override
	public List <Galery> findBySpId(String id) {
		// TODO Auto-generated method stub
		return galeryDao.findByspId(id);
	}

}
