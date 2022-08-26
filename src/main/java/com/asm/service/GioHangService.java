package com.asm.service;

import java.util.List;

import com.asm.entity.GioHang;
import com.asm.entity.NguoiDung;

public interface GioHangService {

	void add(String spId, String ndId);

	void update(Integer id, Integer soluong);

	void remove(Integer id);

	List<GioHang> getItems(String idnd);

	void removebByNguoiDung(String idnd);

}
