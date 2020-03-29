package com.springangular.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Contact {

	@Id
	@GeneratedValue
	private Long id;

	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private String photo;

	private LocalDate dateNaissance;

	public Contact() {

	}

	public Contact(String nom, String prenom, String tel, String email, String photo, LocalDate dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.photo = photo;
		this.dateNaissance = dateNaissance;
	}

}
