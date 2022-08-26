package com.asm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.NguoiDung;

public interface NguoiDungDAO extends JpaRepository<NguoiDung,String> {
	@Query("select distinct o.nguoiDung from UsersRole o "
			+ "where o.role.id in ('ADMIN', 'STAFF')")
	List<NguoiDung> getAdmins();
}
