package com.asm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asm.entity.NguoiDung;
import com.asm.entity.UsersRole;

public interface UsersRoleDAO extends JpaRepository<UsersRole, Integer>{
	@Query(value="SELECT roleid FROM UsersRole WHERE userid = ?1", nativeQuery = true)
	public List<String> findRoleIDByUsername(String uname);
	
	

//	Lấy các quyền được cấp trong nhóm người dùng là admin
	@Query("select distinct o from UsersRole o where o.nguoiDung in ?1")
	public List<UsersRole> authoritiesOf(List<NguoiDung> nguoiDung);
}
