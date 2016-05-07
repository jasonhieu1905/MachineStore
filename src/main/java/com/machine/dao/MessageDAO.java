package com.machine.dao;

import java.util.List;

import com.machine.model.Message;

public interface MessageDAO {
	void addNewMessage(Message message);
	List<Message> getAllMessages();
	void deleteMessage(Message message);
	Message getMessageById(int id);
}
