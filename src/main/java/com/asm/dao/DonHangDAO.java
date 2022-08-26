package com.asm.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.DonHang;
import com.asm.entity.NguoiDung;

public interface DonHangDAO extends JpaRepository<DonHang, String> {

	@Query("SELECT COUNT(o) FROM DonHang o")
	Integer countDonHang();
	
	@Query("SELECT o FROM DonHang o WHERE o.nguoiDung=?1")
	List<DonHang> findByNguoiDung(NguoiDung nguoiDung);
	
	@Query("SELECT o FROM DonHang o WHERE o.daXoa=0")
	List<DonHang> findAllDH();
	
	@Query("SELECT o FROM DonHang o WHERE o.daXoa=1")
	List<DonHang> getDonDaHuy();
}
