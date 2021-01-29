package com.nish.boot.service;

import com.nish.boot.repository.DataBase;
import com.nish.boot.model.Message;
import com.nish.boot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    public Message getMessageById(int id) {
        return messageRepository.findById(id).get();
    }

    public Message createMessage(Message message) {
        messageRepository.save(message);
        return message;
    }

    public Message updateMessage(Message message) {
        messageRepository.save(message);
        return message;
    }

    public Message deleteMessage(int id) {
        Message msg = getMessageById(id);
        messageRepository.delete(msg);
        return msg;
    }



}
