package com.asm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Report implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private SanPham sanPham;
	private double sum;
	private long count;
	

}
