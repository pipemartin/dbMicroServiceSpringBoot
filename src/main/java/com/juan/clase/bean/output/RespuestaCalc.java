package com.juan.clase.bean.output;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RespuestaCalc {
	private int a;
	private int b;
	private int respuesta;
	private String error;
	
	
	public RespuestaCalc(int a, int b, int respuesta, String error) {
		this.a = a;
		this.b = b;
		this.respuesta = respuesta;
		this.error = error;
	}
	public RespuestaCalc(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		//this.error = mensajeError;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
