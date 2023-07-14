package com.shelfShare.shelfShareWeb.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.shelfShare.shelfShareWeb.Entity.User;
import com.shelfShare.shelfShareWeb.Service.UserService;

/*
 * El controller es quien lleva todo el peso referente a las solicitudes HTTP (es el mesero de nuestro restaurante). Aqui sucede la manipulacion de los metodos HTTP (GET, POST, PUT, DELETE).
 * 
 * Para poder decirle a JAVA que esta clase es un controller, necesitamos usar la anotacion @RestController. Esto nos ayudara a poder manejar solicitudes HTTP, y ademas con esta anotacion podremos manejar el fetch que tengamos en nuestro front.
 */


@RequestMapping(path="/shelfshare/users")//construi la ruta de nuestra API

@RestController //Usamos esta anotacion para decirle que es una API del tipo REST(que trabaja con metodos HTTP)

//Anotacion CrossOrigin
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })

public class UserController {
	
	
	//Creo una instancia de la clase UserService para que el controlador sepa la informacion del servicio
	public final UserService userService;
	
	
	@Autowired

	
	//Creo un constructor para mi UserController, tomara como argumento la instancia del userService
	public UserController(UserService userService) {
		this.userService = userService;
	}//constructor que utiliza la instancia del Service como parametro
	
	
	/*
	 * Ya que mi Controller depende de mi Service (con la instancia de UserService dentro de UserController), podre establecer los metodos HTTP que disparan las operaciones del CRUD (Controller - Service)
	 */
	
	
	//Metodos HTTP
	
	/*
	 * Metodo GET que toma la lista de usuarios creada en el userService, y que se muestra cuando se activa el metodo GET
	 */
	
	
	@GetMapping
	public List <User> getUsuarios(){
		return userService.readUsuarios();
	}
	
	
	//Metodo GET para traer un producto por id
		@GetMapping (path="{userId}")
		public Optional<User> getUsuario(@PathVariable("userId") Long id) {
			return userService.leerUsuarioPorID(id);
		}
	
		
		
		//Metodo POST para agregar un producto
		@PostMapping
		public User postUser(@RequestBody User usuario) {
			return userService.crearUsuario(usuario);
		}//post
		
		
		//Metodo PUT para modificar un producto
		//localhost:8080/shelfshare/productos/70
		@PutMapping (path="{userId}") //path para agregarle el id al endpoint
		
		public User updateUser(
				@PathVariable("userId") Long id,
				@RequestParam(required = false) String nombre,
				@RequestParam(required = false) String contrasena) {
			
			return userService.actualizarUsuario(id, nombre, contrasena);
		}//updateProducto
		
		
		
		//Metodo DELETE para borrar un producto por su ID
		
		@DeleteMapping (path="{userId}")
		public User deleteUser(@PathVariable("userId") Long id) {
			return userService.borrarUsuario(id);
		}
	
	

}
