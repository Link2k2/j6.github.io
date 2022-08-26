package com.asm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DONHANG")
public class DonHang {

	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "Ngay")
	private Date ngayTao = new Date();

	@ManyToOne
	@JoinColumn(name = "Userid")
	NguoiDung nguoiDung;

	@Column(name = "Trangthai")
	private Boolean trangThai;

	
	@Column(name = "Hoten")
	private String fullname;
	
	@Column(name = "Sdt")
	private String sdt;
	
	@Column(name = "Diachi")
	private String diaChi;
	
	@Column(name = "Daxoa")
	private Boolean daXoa;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "dh")
	List<DonHangChiTiet> ctdh;

}
