package com.springangular.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springangular.domain.Contact;

@Repository
public interface ContactRepos extends JpaRepository<Contact, Long>{

}
