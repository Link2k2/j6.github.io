package com.asm.service.impl;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.asm.dao.DonHangChiTietDAO;
import com.asm.dao.DonHangDAO;
import com.asm.entity.DonHang;
import com.asm.entity.DonHangChiTiet;
import com.asm.entity.NguoiDung;
import com.asm.service.DonHangService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SessionScope
@Service
public class DonHangImplement implements DonHangService {
	@Autowired
	DonHangDAO dhdao;

	@Autowired
	DonHangChiTietDAO dhctdao;

	@Override
	public DonHang create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		DonHang dh = mapper.convertValue(orderData, DonHang.class);
		Integer stt = dhdao.countDonHang() + 1;
		dh.setId("DH" + stt);
		dhdao.saveAndFlush(dh);
		TypeReference<List<DonHangChiTiet>> type = new TypeReference<List<DonHangChiTiet>>() {
		};

		List<DonHangChiTiet> list = mapper.convertValue(orderData.get("ctdh"), type).stream().peek(d -> d.setDh(dh))
				.collect(Collectors.toList());
		dhctdao.saveAllAndFlush(list);
		return dh;
	}

	@Override
	public DonHang findById(String id) {
		return dhdao.findById(id).get();
	}

	@Override
	public Double amount(String id) {
		List<DonHangChiTiet> dhct = dhctdao.findByDonHang(dhdao.findById(id).get());
		Double amount =  dhct.stream().map(item->item.getSoLuong()*item.getSp().getGia()).reduce(0.0,(total,soLuong)->total+soLuong);
		return amount;
	}

	@Override
	public List<DonHang> findAllDH() {
		// TODO Auto-generated method stub
		return dhdao.findAllDH();
	}

	@Override
	public List<DonHang> findAll() {
		// TODO Auto-generated method stub
		return dhdao.findAll();
	}

	@Override
	public List<DonHang> findByNguoiDung(NguoiDung nguoiDung) {
		// TODO Auto-generated method stub
		return dhdao.findByNguoiDung(nguoiDung);
	}

	@Override
	public List<DonHang> getDonDaHuy() {
		// TODO Auto-generated method stub
		return dhdao.getDonDaHuy();
	}

	@Override
	public Integer countDonHang() {
		// TODO Auto-generated method stub
		return dhdao.countDonHang();
	}

	@Override
	public Boolean existsById(String id) {
		// TODO Auto-generated method stub
		return dhdao.existsById(id);
	}

	@Override
	public DonHang saveAndFlush(DonHang donHang) {
		// TODO Auto-generated method stub
		return dhdao.saveAndFlush(donHang);
	}

	
}
