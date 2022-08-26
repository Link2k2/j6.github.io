package com.asm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOAISANPHAM")
public class LoaiSanPham {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "Id")
	private Integer id;

	@Column(name = "Tenloai")
	private String tenLoai;

	@JsonIgnore
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "loaisp", cascade = CascadeType.ALL)
	List<SanPham> listsp;
}
