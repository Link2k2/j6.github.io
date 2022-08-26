package com.asm.service;

import java.util.List;
import java.util.Optional;

import com.asm.entity.NguoiDung;

public interface NguoiDungService {
	List<NguoiDung> findAll();
	NguoiDung saveAndFlush(NguoiDung nguoiDung);
	NguoiDung save(NguoiDung nguoiDung);
	Optional<NguoiDung> findById(String id);
	List<NguoiDung> getAdmins();
	Boolean existsById(String id);
	void deleteById(String id);
}
