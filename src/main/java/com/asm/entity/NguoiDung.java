package com.asm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NGUOIDUNG")
public class NguoiDung implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -584429757845949593L;

	@Id
	@Column(name = "Id")
	@NotBlank(message = "Không để trống tên đăng nhập (tối thiểu 3 ký tự - tối đa 10 ký tự)*")
	@Length(min = 3, max = 10)
	private String id;

	@Column(name = "Password")
	@NotEmpty(message = "Không để trống mật khẩu (tối thiểu 5 ký tự - tối đa 15 ký tự)*")
	@Length(min = 5, max = 15)
	private String password;

	@Column(name = "Ho")
	@NotBlank(message = "Không để trống họ*")
	private String ho;

	@Column(name = "Ten")
	@NotBlank(message = "Không để trống tên*")
	private String ten;

	@Column(name = "Gioitinh")
	private Boolean gioiTinh = true;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "Ngaysinh")
	@Temporal(TemporalType.DATE)
	private Date ngaySinh = new Date();


	@Column(name = "Email")
	@NotBlank(message = "Không để trống email*")
	private String email;

	@Column(name = "Cmnd")
	@NotBlank(message = "Không để trống CMND/CCCD*")
	private String cmnd;

	@Column(name = "Sdt")
	@NotBlank(message = "Không để trống số điện thoại*")
	@Length(min = 10, max = 11)
	private String sdt;

	@Column(name = "Diachi")
	@NotBlank(message = "Không để trống địa chỉ*")
	private String diaChi;

	@Column(name = "Trangthai")
	private Boolean trangThai;

	@Column(name = "Daxoa")
	private Boolean daXoa;

	@JsonIgnore
	@OneToMany(mappedBy = "nguoiDung")
	List<GioHang> gioHang;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "nguoiDung")
	List<Favorite> favorite;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "nguoiDung")
	List<DonHang> donhang;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "nguoiDung", cascade = CascadeType.ALL)
	List<UsersRole> userRole;

}
