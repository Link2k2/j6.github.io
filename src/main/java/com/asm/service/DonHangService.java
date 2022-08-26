package com.asm.service;

import java.util.List;

import com.asm.entity.DonHang;
import com.asm.entity.NguoiDung;
import com.fasterxml.jackson.databind.JsonNode;

public interface DonHangService {
	
	DonHang create(JsonNode orderData);

	DonHang findById(String id);

	Double amount(String id);
	
	List<DonHang> findAllDH();
	
	List<DonHang> findAll();
	
	List<DonHang> findByNguoiDung(NguoiDung nguoiDung);
	
	List<DonHang> getDonDaHuy();
	
	Integer countDonHang();
	
	Boolean existsById(String id);
	
	DonHang saveAndFlush(DonHang donHang);

}
