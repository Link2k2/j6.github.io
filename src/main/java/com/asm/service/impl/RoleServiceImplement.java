package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.RoleDAO;
import com.asm.entity.Role;
import com.asm.service.RoleService;

@Service
public class RoleServiceImplement implements RoleService{
	
	@Autowired
	RoleDAO roleDao;
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}

	

}
