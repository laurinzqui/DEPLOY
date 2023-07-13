package com.shelfShare.shelfShareWeb.Repository;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.shelfShare.shelfShareWeb.Entity.User;



@Repository //Anotacion para convertir mi interface en un repositorio

public interface UserRepository extends JpaRepository<User, Long>{
	
	
	
	
	Optional<User> findByEmail(String Email);
	

	//Query personalizada
	//@Query("SELECT usuario FROM User WHERE usuario.nombre=?1")
	
	//Optional<User> encontrarPorEmail (String email);
}