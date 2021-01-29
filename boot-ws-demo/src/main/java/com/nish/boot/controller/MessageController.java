package com.nish.boot.controller;

import com.nish.boot.model.Message;
import com.nish.boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable("messageId") int id) {
        return messageService.getMessageById(id);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/messages/{messageId}")
    public Message updateMessage(@RequestBody Message message, @PathVariable("messageId") int id) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

//    @RequestMapping(method = RequestMethod.PATCH, value = "/messages/{messageId}")
    @PatchMapping("/messages/{messageId}")
    public Message updateMessageText(@RequestBody String messageText, @PathVariable("messageId") int id) {
        Message message =  messageService.getMessageById(id);
        message.setMessage(messageText);
        return messageService.updateMessage(message);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/messages/{messageId}")
    public Message deleteMessage(@PathVariable("messageId") int id) {
        return messageService.deleteMessage(id);
    }

}

