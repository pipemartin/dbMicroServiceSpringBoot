package com.juan.clase.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.juan.clase.bean.db.Celular;
import com.juan.clase.bean.db.User;
import com.juan.clase.bean.input.Input;
import com.juan.clase.bean.output.Respuesta;
import com.juan.clase.bean.output.RespuestaCalc;
import com.juan.clase.repository.UserRepository;

@Controller 					
@RequestMapping(path = "/user")
public class MainController {
	 
	
	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private UserRepository userRepository;
	

	@PostMapping(path = "/add") 
	public @ResponseBody void addNewUser(@RequestParam Integer cedula,
										   @RequestParam String nombre, 
										   @RequestParam String email,
										   @RequestParam Celular celular,
										   @RequestParam String fechaNacimiento) {
		//Respuesta respuesta = new Respuesta();
		if(nombre == null) {
			//respuesta.setRespuesta("Error Digitar el nombre");
			//respuesta.setError("Digitar el nombre");
			//return respuesta;
		} 
		
		User userNuevo = new User(cedula,cedula, nombre, email, celular, fechaNacimiento);
		
		try {
			userRepository.save(userNuevo);
			//respuesta.setRespuesta("Usuario Guardado");
		} catch(Exception e) {
			//respuesta.setRespuesta("Error en agregar Usuario");
			//respuesta.setError(e.getMessage());
		}
		
		//return respuesta;
	}
	
	@PostMapping(path = "/addSecond", consumes="application/json", produces="application/json")
	public @ResponseBody User addNewUserSecond(@Valid @RequestBody User user){
		try {
			logger.info("Se Registro Correctamente");
			return userRepository.save(user);

		} catch (Exception e) {
			e.getCause();
			logger.info(e.getMessage());
			return user;
		}
	}
	
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	
	@PostMapping(path = "/createUserCel", consumes="application/json", produces="application/json")
	public User createUserCel (@RequestBody User user) {
		return userRepository.save(user);
	}
	
	
	@GetMapping(path = "/remove", consumes="application/json", produces="application/json")
	public @ResponseBody void removeUser (@RequestBody User user){
		//Respuesta respuesta = new Respuesta();
		try {
			
			logger.info("Borrando al Usuario: "+user.getId_user());
	        userRepository.deleteById(user.getId_user());
	       // respuesta.setRespuesta("Borrado con exito del usuario: "+user.getId());
	        ///respuesta.setError("No hay errores");
	        //return respuesta;
		} catch (Exception e) {
			//respuesta.setRespuesta("Usuario No Existe: "+user.getId());
//			respuesta.setError(e.getMessage());
//			return respuesta;
		}
		  
    }
	
	@GetMapping(path = "/find", consumes="application/json", produces="application/json")
	public @ResponseBody Optional<User> findUser (@RequestBody User user){
		try {
			logger.info("Consultando Usuario: "+user.getId_user());
			return userRepository.findById(user.getId_user());
		} catch (Exception e) {
			logger.info(e.getMessage());
			return Optional.empty();
		}
        
    }
	
	@GetMapping(path = "/consulta", consumes="application/json", produces="application/json")
	public @ResponseBody Respuesta consultarUser (@RequestBody Input input){
		Respuesta res = new Respuesta();
		// Buscar a la persona 
		Optional<User> users = userRepository.findById(Integer.parseInt(input.getId_user()));
		if(!users.isEmpty()) {
			User user = users.get();
			res.setPersona(user);
		}
		
		// Invocar el micro de calculadora
		String url = "http://localhost:8080/"+input.getOperacion()+"/"+input.getUno()+"/"+input.getDos();
		RestTemplate restTemplate = new RestTemplate();	
		ResponseEntity<RespuestaCalc> resCalculadora = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {   });
	
		// Armar la respuesta
		res.setResultado(Double.valueOf(resCalculadora.getBody().getRespuesta()));
		return res;
    }
	
}

