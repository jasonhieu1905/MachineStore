package com.machine.service;

import java.util.List;

import com.machine.model.Message;

public interface MessageService {
	void addNewMessage(Message message);
	List<Message> getAllMessages();
	void deleteMessage(Message message);
	public Message getMessageById(int id);
}
