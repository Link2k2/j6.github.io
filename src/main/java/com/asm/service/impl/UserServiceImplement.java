package com.asm.service.impl;

import java.util.Base64;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.asm.dao.NguoiDungDAO;
import com.asm.dao.UsersRoleDAO;
import com.asm.entity.NguoiDung;
import com.asm.entity.UsersRole;
import com.asm.service.UserService;
import com.asm.service.UsersRoleService;

@Service
public class UserServiceImplement implements UserService{
	@Autowired
	HttpSession session;
	
	@Autowired
	NguoiDungDAO ndDao;
	
	@Autowired
	UsersRoleDAO userRoleDao;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
try {
			
			NguoiDung sv = ndDao.findById(username).get();
			System.out.println(username);
			
			String password = sv.getPassword();
			
			String[] role = userRoleDao.findRoleIDByUsername(username).toArray(new String[0]);
			this.setToken(username, password);
			return User.withUsername(username)
					.password(pe.encode(password))
					.roles(role).build();// luôn phải mã hóa mật khẩu 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw new UsernameNotFoundException(username + " not found");
			
		}
	}

	@Override
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		// TODO Auto-generated method stub
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis()); 
		
		UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("USER").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		this.setToken(email, password);
	}

	@Override
	public void setToken(String username, String password) {
		// TODO Auto-generated method stub
		byte[] auth = (username + ":" + password).getBytes();
		String token = "Basic " + Base64.getEncoder().encodeToString(auth);
		session.setAttribute("token", token);
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return (String) session.getAttribute("token");

	}


	

	

}
