package com.asm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.NguoiDungDAO;
import com.asm.dao.UsersRoleDAO;
import com.asm.entity.NguoiDung;
import com.asm.entity.UsersRole;
import com.asm.service.UsersRoleService;

@Service
public class UserRoleServiceImplement implements UsersRoleService{
	@Autowired
	UsersRoleDAO userRoleDao;
	
	@Autowired
	NguoiDungDAO ndDao;
	
	@Override
	public List<NguoiDung> getAdmin() {
		// TODO Auto-generated method stub
		return ndDao.getAdmins();
	}

	@Override
	public List<UsersRole> findAuthoritiesOfAdmin() {
		// TODO Auto-generated method stub
		List<NguoiDung> nguoiDung = ndDao.getAdmins();
		return userRoleDao.authoritiesOf(nguoiDung);
	}

	@Override
	public UsersRole create(UsersRole user) {
		// TODO Auto-generated method stub
		return userRoleDao.saveAndFlush(user);
	}

	@Override
	public List<UsersRole> findAll() {
		// TODO Auto-generated method stub
		return userRoleDao.findAll();
	}

	@Override
	public Optional<UsersRole> findById(Integer id) {
		// TODO Auto-generated method stub
		return userRoleDao.findById(id);
	}

	@Override
	public Boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return userRoleDao.existsById(id);
	}

	@Override
	public UsersRole saveAndFlush(UsersRole uRole) {
		// TODO Auto-generated method stub
		return userRoleDao.saveAndFlush(uRole);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		userRoleDao.deleteById(id);
	}
}
