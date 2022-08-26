package com.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.asm.entity.NguoiDung;
import com.asm.entity.UsersRole;

public interface UsersRoleService {

	
	 
	  List<NguoiDung> getAdmin();

	 List<UsersRole> findAuthoritiesOfAdmin();

	 UsersRole create(UsersRole user);
	
 List<UsersRole> findAll();
	 
	 Optional<UsersRole> findById(Integer id);
	 
	 Boolean existsById(Integer id);
	 
	 UsersRole saveAndFlush( UsersRole uRole);
	 
	 void deleteById(Integer id);
}
