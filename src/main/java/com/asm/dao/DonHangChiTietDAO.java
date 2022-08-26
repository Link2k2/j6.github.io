package com.asm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.DonHang;
import com.asm.entity.DonHangChiTiet;
import com.asm.entity.Report;


public interface DonHangChiTietDAO extends JpaRepository<DonHangChiTiet, Integer> {
	@Query("SELECT o FROM DonHangChiTiet o WHERE o.dh=?1")
	List<DonHangChiTiet> findByDonHang(DonHang dh);
	
	@Query("SELECT new Report(d.sp, sum(d.soLuong * d.sp.gia), sum(d.soLuong)) FROM DonHangChiTiet d GROUP BY d.sp")
	List<Report> revenueByProduct();
}
