package com.asm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SANPHAM")
public class SanPham implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3768438804834207003L;

	@Id
	@Column(name = "Id")
	private String id;

	
	@ManyToOne
	@JoinColumn(name = "LoaiSPID")
	LoaiSanPham loaisp;

	@Column(name = "Tensp")
	private String tenSP;

	@Column(name = "Soluong")
	private Integer soLuong;

	@Column(name = "Gia")
	private Double gia;

	@Column(name = "Discount")
	private Double discount;

	@Column(name = "Thongtinct")
	private String thongTinCT;

	@Column(name = "Ngaytao")
	@Temporal(TemporalType.DATE)
	private Date ngayTao;

	@Column(name = "Ngaysua")
	@Temporal(TemporalType.DATE)
	private Date ngaySua;

	@Column(name = "Dvt")
	private String dVT;

	@Column(name = "Daxoa")
	private Boolean daXoa;
	
	@Column(name="Image")
	private String image;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sp")
	List<Galery> listsp;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sp")
	List<GioHang> listCTGH;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sp")
	List<Favorite> favorite;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "sp")
	List<DonHangChiTiet> donhangct;
}
