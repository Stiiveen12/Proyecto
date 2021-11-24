package com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.model.Cliente;


public interface ClienteRepository  extends MongoRepository<Cliente, String>{
	
	List<Cliente> findByCedulacliente(Integer codigoproducto);
	
	List<Cliente> findByEmailcliente(String emailcliente);


}
