package com.springangular.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springangular.controller.errors.BadRequestAlertException;
import com.springangular.domain.Contact;
import com.springangular.service.ContactService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;


@RestController
@RequestMapping("/api")
public class ContactController {

	private final Logger log = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	ContactService contactService;

	private static final String ENTITY_NAME = "contacts";

	private String applicationName = "contacts";

	/**
	 * {@code GET  /contacts} : get all the contacts.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of contacts in body.
	 */
	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		log.debug("REST request to get all Contacts");
		return contactService.findAll();
	}
	
	 /**
     * {@code GET  /contacts/:id} : get the "id" contact.
     *
     * @param id the id of the contact to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contact, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        log.debug("REST request to get contacts : {}", id);
        Optional<Contact> contacts = contactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contacts);
    }
    
    /**
     * {@code DELETE  /contacts/:id} : delete the "id" contact.
     *
     * @param id the id of the contact to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.debug("REST request to delete contacts : {}", id);
        contactService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

	/**
	 * {@code POST  /contacts} : Create a new contact.
	 *
	 * @param contact the contact to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new contact, or with status {@code 400 (Bad Request)} if the
	 *         contact has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/contacts")
	public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) throws URISyntaxException {
		log.debug("REST request to save contact : {}", contact);
		if (contact.getId() != null) {
			throw new BadRequestAlertException("A new contact cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Contact result = contactService.save(contact);
		return ResponseEntity
				.created(new URI("/api/contacts/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

}
