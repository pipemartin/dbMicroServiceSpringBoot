package com.juan.clase.bean.output;

import org.springframework.context.annotation.Configuration;

import com.juan.clase.bean.db.User;

@Configuration
public class Respuesta {
	private String error;
	private Double resultado;
	private User persona;
	
//	@Value("${app.message,error}")
//	private String mensajeError;
	
	public Respuesta(String error) {
		super();
		this.error = error;
	}
	
	

	public Respuesta() {
		super();
	}

	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public Double getResultado() {
		return resultado;
	}



	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}


	public User getPersona() {
		return persona;
	}

	public void setPersona(User persona) {
		this.persona = persona;
	}
	
	
}
