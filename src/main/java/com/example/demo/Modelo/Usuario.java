package com.example.demo.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    public Usuario(String nombre) {
        this.nombre=nombre;
	}
    public Usuario() {

	}

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    public long getId() {
		return id;
	}
    public String getNombre() {
		return nombre;
	}
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
