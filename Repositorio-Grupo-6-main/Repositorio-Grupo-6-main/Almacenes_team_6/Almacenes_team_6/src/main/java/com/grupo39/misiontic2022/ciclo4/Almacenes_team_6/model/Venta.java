package com.grupo39.misiontic2022.ciclo4.Almacenes_team_6.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_ventas")
public class Venta {
	
	@Id
	private String id;

}
