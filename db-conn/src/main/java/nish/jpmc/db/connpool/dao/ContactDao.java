package nish.jpmc.db.connpool.dao;

import nish.jpmc.db.connpool.model.Contact;

import java.util.List;

public interface ContactDao {

    public List<Contact> getAllContacts();

    public Contact findContactById(int id);

    public void addContact(Contact employee);

    public void updateContact(Contact employee);

    public void deleteContact(int id);

}