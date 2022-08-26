package com.asm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.asm.dao.GioHangDAO;
import com.asm.dao.NguoiDungDAO;
import com.asm.dao.SanPhamDAO;
import com.asm.entity.GioHang;
import com.asm.entity.NguoiDung;
import com.asm.entity.SanPham;
import com.asm.service.GioHangService;

@SessionScope
@Service
public class GioHangImplement implements GioHangService {
	@Autowired
	GioHangDAO dao;

	@Autowired
	NguoiDungDAO nddao;

	@Autowired
	SanPhamDAO spdao;

	@Override
	public void add(String spId, String ndId) {
		NguoiDung nd = nddao.findById(ndId).get();
		SanPham sp = spdao.findById(spId).get();
		GioHang gio = new GioHang();
		gio.setSp(sp);
		gio.setNguoiDung(nd);
		GioHang existItem = dao.findBySPTrongGio(nd, sp);
		if (existItem != null) {
			existItem.setSoLuong(existItem.getSoLuong() + 1);
			dao.saveAndFlush(existItem);
		} else {
			gio.setSoLuong(1);
			dao.saveAndFlush(gio);
		}

	}

	@Override
	public void update(Integer id, Integer soluong) {
		if (dao.existsById(id)) {
			GioHang gio = dao.findById(id).get();
			gio.setSoLuong(soluong);
			dao.saveAndFlush(gio);
		}
	}

	@Override
	public void remove(Integer id) {
		if (dao.existsById(id)) {
			dao.deleteById(id);
		}
	}

	@Override
	public void removebByNguoiDung(String idnd) {
		NguoiDung nd = nddao.findById(idnd).get();
		dao.deleteByNguoiDung(nd);
	}

	@Override
	public List<GioHang> getItems(String idnd) {
		NguoiDung nd = nddao.findById(idnd).get();
		return dao.findByNguoiDung(nd);
	}

}
