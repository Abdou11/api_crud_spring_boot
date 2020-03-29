package com.springangular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springangular.domain.Contact;
import com.springangular.repos.ContactRepos;

@Service
public class ContactService {

	@Autowired
	private ContactRepos contactRepos;

	public List<Contact> findAll() {
		return contactRepos.findAll();
	}

	public Contact save(Contact contact) {
		return contactRepos.save(contact);
	}

	public Optional<Contact> findOne(Long id) {
		return contactRepos.findById(id);
	}

	public void delete(Long id) {
		contactRepos.deleteById(id);
	}

}
