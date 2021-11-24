package com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.model.Cliente;
import com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.repository.ClienteRepository;


@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes() {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			clienteRepository.findAll().forEach(clientes::add);
			
			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/clientes/{id}")
	  public ResponseEntity<Cliente> getClientesById(@PathVariable("id") String id) {
	    Optional<Cliente> clienteData = clienteRepository.findById(id);

	    if (clienteData.isPresent()) {
	      return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/clientes")
	  public ResponseEntity<Cliente> createClientes(@RequestBody Cliente cliente) {
	    try {
	    	Cliente _cliente = clienteRepository.save(new Cliente(cliente.getCedulacliente(), cliente.getDireccioncliente(), cliente.getEmailcliente(), cliente.getNombrecliente(), cliente.getTelefonocliente()));
	      return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/clientes/{id}")
	  public ResponseEntity<Cliente> updateClientes(@PathVariable("id") String id, @RequestBody Cliente cliente) {
	    Optional<Cliente> clienteData = clienteRepository.findById(id);

	    if (clienteData.isPresent()) {
	    	Cliente _cliente = clienteData.get();
	    	_cliente.setCedulacliente(cliente.getCedulacliente());
	    	_cliente.setDireccioncliente(cliente.getDireccioncliente());
	    	_cliente.setEmailcliente(cliente.getEmailcliente());
	    	_cliente.setNombrecliente(cliente.getNombrecliente());
	    	_cliente.setTelefonocliente(cliente.getTelefonocliente());
	      return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/clientes/{id}")
	  public ResponseEntity<HttpStatus> deleteClientes(@PathVariable("id") String id) {
	    try {
	    	clienteRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("/clientes")
	  public ResponseEntity<HttpStatus> deleteAllClientes() {
	    try {
	    	clienteRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/clientes/{correo}")
	  public ResponseEntity<List<Cliente>> findByCorreo(@PathVariable("correo") String correo) {
	    try {
	      List<Cliente> clientes = clienteRepository.findByEmailcliente(correo);

	      if (clientes.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(clientes, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
