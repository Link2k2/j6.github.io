package com.asm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.GioHang;
import com.asm.entity.NguoiDung;
import com.asm.entity.SanPham;

public interface GioHangDAO extends JpaRepository<GioHang, Integer> {
	@Query("SELECT o FROM GioHang o WHERE o.nguoiDung=?1 ")
	List<GioHang> findByNguoiDung(NguoiDung nd);

	@Query("SELECT o FROM GioHang o WHERE o.nguoiDung=?1 and o.sp=?2")
	GioHang findBySPTrongGio(NguoiDung nd, SanPham sp);

	@Transactional
	@Modifying
	@Query("DELETE FROM GioHang o WHERE o.nguoiDung=?1")
	void deleteByNguoiDung(NguoiDung nd);

}
