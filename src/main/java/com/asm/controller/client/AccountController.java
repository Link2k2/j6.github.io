package com.asm.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.dao.NguoiDungDAO;
import com.asm.dao.RoleDAO;
import com.asm.entity.NguoiDung;
import com.asm.entity.Role;
import com.asm.entity.UsersRole;
import com.asm.service.MailerService;
import com.asm.service.NguoiDungService;
import com.asm.service.UsersRoleService;

import kotlin.OptIn;


@Controller
public class AccountController {
	

	@Autowired
	MailerService mailer;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	NguoiDungService ndService;
	
	@Autowired
	UsersRoleService uRoleService;
	
	
	@Autowired
	RoleDAO roleDao;

	@GetMapping("/auth/login")
	public String formLogin() {
		

		return "account/login";
	}
	@GetMapping("/auth/login/form")
	public String formLogin2() {
		

		return "account/login";
	}
	
	@PostMapping("/auth/login/success")
	public String authLoginSuccess(Model model) {
		// TODO Auto-generated constructor stub
		return "redirect:/home/index";
	}
	
	
	
	

	@RequestMapping("/auth/access/denied")
	public String authLoginDenied(Model model) {
		// TODO Auto-generated constructor stub
		model.addAttribute("message", "Bạn không có quyền truy xuất!");
		return "auth/login";
	}

	@RequestMapping("/auth/logoff/success")
	public String authLoginLogoff(Model model) {
		// TODO Auto-generated constructor stub
		
		return "redirect:/home/index";
	}

	@RequestMapping("/auth/register")
	public String register( @ModelAttribute("nguoiDung") Optional<NguoiDung> nguoiDung) {
		NguoiDung nd = nguoiDung.get();  
		nd.setCmnd("000000000000");
		nd.setDaXoa(false);
		nd.setSdt("0000000000");
		nd.setTrangThai(true);
		
		
		return "account/register";
	}
	
	@PostMapping("/auth/register")
	public String postRegister(@Validated @ModelAttribute("nguoiDung") Optional<NguoiDung> nguoiDung,BindingResult result ,Errors error,  Model model 
			) {
		
		if(result.hasErrors()) {
			model.addAttribute("messate", "Đăng Ký 0 thành công");
			 return "account/register";
		}else if(nguoiDung.isPresent()){
			
			NguoiDung nd = nguoiDung.get();  
			Optional<NguoiDung> check = ndService.findById(nd.getId());
				if(check ==null) {
					
					ndService.saveAndFlush(nd);
					UsersRole uRole = new UsersRole();
					Role rolel = new Role();
					List<Role> listRole =  roleDao.findAll();
					listRole.forEach(role->{
						if(role.getId().equalsIgnoreCase("USER")) {
							rolel.setId(role.getId());
							rolel.setName(role.getName());
						}
					});
					uRole.setNguoiDung(nd);
					uRole.setRole(rolel);
					uRoleService.create(uRole);
					model.addAttribute("messate", "Đăng Ký thành công");
					
				}
				else {
					model.addAttribute("messate", "Trùng tên đăng nhập");
				}
				NguoiDung reset = new NguoiDung();
				model.addAttribute("nguoiDung", reset);
				
		}
		
		
		return "account/register";
	}
	
	@GetMapping("/auth/forgot-password")
	public String forgotPass(Model model, NguoiDung ngDung,  @ModelAttribute("forgot") NguoiDung form  ) {
	
		
		return "account/forgot";
	}
	
	@PostMapping("/auth/get-forgot-password")
	public String forgotPassword( @Validated @ModelAttribute("forgot") NguoiDung form,BindingResult result, Errors errors,Model model){
		
		
		
			
				Optional<NguoiDung> findUser = ndService.findById(form.getId());
				
				if(findUser !=null) {
					NguoiDung nguoiDung = findUser.get();
					
					if(nguoiDung.getEmail().equals(form.getEmail())){
						try {System.out.println("asdasd3");
							mailer.send(form.getEmail(), 
									"[No-reply] Mật khẩu của bạn", 
									"Đây là mật khẩu của bạn: "+ nguoiDung.getPassword());
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						model.addAttribute("PassID", "Đã gửi mật khẩu trong email");

					}
					else {
						model.addAttribute("errorEmail", "Sai Email");

					}
					
					
				}else {
					model.addAttribute("errorId", "ssTài khoản này không có trong hệ thống");

				}
			
		
		return "account/forgot";
	}

	@RequestMapping("/home/account/my-profile")
	public String ViewmyProfile(Model model, NguoiDung ngDung) {
	
		NguoiDung nd = ndService.findById(request.getRemoteUser()).get();
		
		model.addAttribute("myprofile", nd);
		
		return "account/_myprofile";
	}
	
	@PostMapping("/home/account/my-profile")
	public String myProfile(Model model, NguoiDung ngDung, @Validated @ModelAttribute("myprofile") NguoiDung form, Errors errors) {
		if (errors.hasErrors()) {
			model.addAttribute("message","Vui lòng sửa các lỗi sau: ");
			return"account/_myprofile";
		}else {
			ngDung.setDaXoa(false);
			ngDung.setTrangThai(true);
			
			ndService.save(ngDung);
		}
		
		return "account/_myprofile";
	}
	@GetMapping("/home/account/change-password")
	public String ViewChange(Model model ) {
		
		NguoiDung ngDung = new NguoiDung();
		model.addAttribute("nguoiDung", ngDung);
		
		return "account/change";
	}
	
	@PostMapping("/home/account/change-password")
	public String changePassword( @Validated @ModelAttribute("nguoiDung") Optional<Object> form,Errors errors,Model model
			,BindingResult result) {
		String pass = request.getParameter("newPassword");
		if(result.hasErrors()) {
			 return "account/change";
		}
		if(form.isPresent()) {
			NguoiDung nd = ndService.findById(request.getRemoteUser()).get();
			nd.setPassword(pass);
			ndService.saveAndFlush(nd);
		}
		
		model.addAttribute("errors", "Đổi mật khẩu thành công ");
		return "account/change";
	}

	

	
}
