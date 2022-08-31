package com.juan.clase.bean.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity 
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_user;
	
	//@Max(value = 10)
	@NotNull(message="Cedula is null")
	@Column(unique = true)
	private Integer cedula;
	
	
	@NotNull
	//@Pattern(regexp = "[a-zA-Z]")
	private String nombre;
	
	
	@NotNull(message="Email is null")
	@Email
	private String email;
	
	
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "celular_id")
	private Celular celular;
	
	
	
	@NotNull
	@Column(name = "fecha_nacimiento")
	private String fechaNacimiento;
	
}
