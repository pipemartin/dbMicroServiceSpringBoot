package com.juan.clase.bean.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Celular {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_celular;
	
	@Column(unique = true)
	private Integer numero;
	
	@NotNull()
	private String marca;
	
	@NotNull()
	private String modelo;
	
	@OneToOne(mappedBy = "celular")
	private User user;
}
