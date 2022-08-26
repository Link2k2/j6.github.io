package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm.dao.DonHangChiTietDAO;
import com.asm.entity.Report;
import com.asm.service.ReportService;

@Service
public class ReportServiceImplement implements ReportService{

	@Autowired
	DonHangChiTietDAO dhctDao;
	
	@Override
	public List<Report> findReport() {
		// TODO Auto-generated method stub
		return dhctDao.revenueByProduct();
	}

	

}
