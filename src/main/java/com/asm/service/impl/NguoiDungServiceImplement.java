package com.asm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.NguoiDungDAO;
import com.asm.entity.NguoiDung;
import com.asm.service.NguoiDungService;

@Service
public class NguoiDungServiceImplement implements NguoiDungService{
	
	@Autowired
	NguoiDungDAO ndDao;
	
	@Override
	public List<NguoiDung> findAll() {
		// TODO Auto-generated method stub
		return ndDao.findAll();
	}

	@Override
	public NguoiDung saveAndFlush(NguoiDung nguoiDung) {
		// TODO Auto-generated method stub
		return ndDao.saveAndFlush(nguoiDung);
	}

	@Override
	public NguoiDung save(NguoiDung nguoiDung) {
		// TODO Auto-generated method stub
		return ndDao.save(nguoiDung);
	}

	@Override
	public Optional<NguoiDung> findById(String id) {
		// TODO Auto-generated method stub
		Optional<NguoiDung> nd= ndDao.findById(id);
		if(nd.isPresent()) {
			return ndDao.findById(id);
		}
		
		return null;
	}

	@Override
	public List<NguoiDung> getAdmins() {
		// TODO Auto-generated method stub
		return ndDao.getAdmins();
	}

	@Override
	public Boolean existsById(String id) {
		// TODO Auto-generated method stub
		return ndDao.existsById(id);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		ndDao.deleteById(id);
	}

	
}
