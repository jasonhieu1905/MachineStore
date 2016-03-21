package com.machine.service;

import com.machine.model.Contact;

public interface ContactService {

	Contact getContact();
	void updateContact(Contact contact);
}
