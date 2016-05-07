package com.machine.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.machine.dao.MessageDAO;
import com.machine.model.Message;
import com.machine.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	private MessageDAO messageDAO;
	
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	@Override
	public void addNewMessage(Message message) {
		messageDAO.addNewMessage(message);
	}

	@Override
	public List<Message> getAllMessages() {
		return messageDAO.getAllMessages();
	}

	@Override
	public void deleteMessage(Message message) {
		messageDAO.deleteMessage(message);
	}

	@Override
	public Message getMessageById(int id) {
		return messageDAO.getMessageById(id);
	}

}
