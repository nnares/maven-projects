package com.nish.boot.service;

import com.nish.boot.model.Message;
import com.nish.boot.repository.DataBase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceHardCodedValue {

    private Map<Integer, Message> messageTable = DataBase.getMessageTable();

    public Map<Integer, Message> getMessageTable() {
        return messageTable;
    }

    public void setMessageTable(Map<Integer, Message> messageTable) {
        this.messageTable = messageTable;
    }

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>(messageTable.values());
        return messages;
    }

    public Message getMessageById(int id) {
        return messageTable.get(id);
    }

    public Message createMessage(Message message) {
        message.setId(messageTable.size() + 1);
        messageTable.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messageTable.put(message.getId(), message);
        return message;
    }

    public Message deleteMessage(int id) {
        return messageTable.remove(id);
    }



}
