package com.asm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role {
	
	@Id
	@Column(name = "Id")
	private String id;
	
	@Column(name="Name")
	private String name;
	
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "role")
	List<UsersRole> userRole;
	

}
