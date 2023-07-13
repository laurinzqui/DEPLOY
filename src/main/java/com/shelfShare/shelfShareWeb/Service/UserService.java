package com.shelfShare.shelfShareWeb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;//importacion de la libreria de Spring

import com.shelfShare.shelfShareWeb.Entity.User;
import com.shelfShare.shelfShareWeb.Repository.UserRepository;



/*
 * Recuerda que la capa de servicios, se encarga de definir la logica de negocio del lado de Java (aqui es donde vamos a definir los metodos enfocados a las operaciones del CRUD que seran "disparados" por los metodos HTTP que se encontraran en el Controller
 */


//Anotaciones son instrucciones que le damos a JAVA para que sepa hacer algo (@Test, @Override)


@Service //Usamos la anotacion @Service para decirle a Java que esta clase es un servicio
public class UserService {

	
	
	//Constante para el autowired
	private final UserRepository userRepository;
	
	//Anotacion
	@Autowired
	
	//Constructor donde se inyecta la dependencia (constante)
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	

	//Metodos CRUD para poder iterar sobre esos usuarios
	
	
	//Metodo para leer todos los usuarios de la BD
	public List<User> readUsuarios(){
		return userRepository.findAll();
	}
	
	
	//Metodo para leer un producto por su Id
		public Optional<User> leerUsuarioPorID(Long id) {
			return userRepository.findById(id);
		}
		
	
	
	// Gatito Nenito = new Gatito(parametros)
		public User crearUsuario(User usuario) { //le pasamos la instancia de un producto como parametro
			
			User prodTemporal = null; //creamos un objeto temporal para no modificar aun la base de datos
			//busco dentro del repositorio un producto por nombre, este dato lo obtengo con el getter correspondientes, y tambien hago una segunda validacion para verificar que el opcional esta vacio y puedo agregar el dato
			if (userRepository.findByEmail(usuario.getEmail()).isEmpty()) {
				prodTemporal = userRepository.save(usuario);
			} else {
				// si el producto ya existe, imprimo mensaje de "producto ya existente"
				System.out.println("Ya existe un usuario con el Email " + usuario.getEmail());
			}
			
			return prodTemporal; //puede que el producto se quede null o no
			
		}//public crearProducto
		
		
		
		//Metodo para modificar un producto
		public User actualizarUsuario(Long id, String nombre, String contrasena) {
			
			User prodTemporal = null; //declaro producto temporal con valor nulo, para que sirva como mi calca
			
			//Si el producto existe, lo modifico
			if (userRepository.existsById(id)) { //true
				//hago la modificacion de sus parametros
				prodTemporal = userRepository.findById(id).get();
				if (nombre!=null) prodTemporal.setNombre(nombre);
				if (contrasena!=null) prodTemporal.setContrasena(contrasena);
				
				userRepository.save(prodTemporal);	
			} else {
				//Si el producto no existe, no lo puedo modificar y mando un mensaje que diga "el producto no existe"
				System.out.println("El producto que quieres actualizar, no existe");
			}//else
			return prodTemporal; //nulo o el producto modificado
			
		}//public actualizarProducto
		
		
		
		//Metodo para borrar un producto
		public User borrarUsuario(Long id) {
			
			User prodTemporal = null;
			
			//Si el producto existe, lo borro
			
			if (userRepository.existsById(id)) {
				prodTemporal = userRepository.findById(id).get();
				userRepository.deleteById(id);
			}//if
			
			return prodTemporal;
			
		}//borrarProducto
	

	
	
	
	
	
}//clase UserService