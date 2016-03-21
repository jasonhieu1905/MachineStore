package com.machine.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.ContactDAO;
import com.machine.model.Contact;
import com.machine.service.ContactService;


@Service
@Transactional
public class ContactServiceImpl implements ContactService{
	private ContactDAO contactDAO;
	
	
	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	@Override
	public Contact getContact() {
		return contactDAO.getContact();
	}

	@Override
	public void updateContact(Contact contact) {
		 contactDAO.updateContact(contact);
	}

	
}
