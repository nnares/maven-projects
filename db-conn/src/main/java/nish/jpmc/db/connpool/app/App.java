package nish.jpmc.db.connpool.app;

import nish.jpmc.db.connpool.dao.ContactDaoImpl;
import nish.jpmc.db.connpool.model.Contact;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/*
 * SAVING WITHOUT HIBERNATE - USING JDBC
 * 1. JDBC Database Configuration
 * 2. The Model Object
 * 3. Service method to create the model object
 * 4. Database Design
 * 5. DAO method to save the object using SQL query
 *
 * */
public class App {


    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        int id;
        Contact contact;
        ContactDaoImpl contactDao = (ContactDaoImpl) context.getBean("dao");


        List<Contact> allContacts = contactDao.getAllContacts();
        System.out.println("------------All Contacts-------------- ");
        allContacts.forEach(System.out::println);


        id= 54;
        contact = contactDao.findContactById(id);
        System.out.println("Finding contact by id - " + id + "\n contact - " + contact);

        // UPDATE
        // Contact{contactId=54, name='Subhi Ojha', email='subhi.ojha@example.com', address='Kankarbagh, Patna', phone='9955995599'}
/*
        id = 54;
        contact = new Contact();
        contact.setContactId(id);
        contact.setName("Subhi Ojha");
        contact.setEmail("subhi.ojha@example.com");
        contact.setAddress("Kankarbagh, Patna");
        contact.setPhone("9955995599");
        contactDao.updateContact(contact);
        System.out.println("Updated contact with id - " + id + "\n contact - " + contactDao.findContactById(id));
*/

        // ADD
/*
        id = 55;
        contact = new Contact();
        contact.setContactId(id);
        contact.setName("Shailendra Pandey");
        contact.setEmail("shailendra.pandey@example.com");
        contact.setAddress("Satna, MP");
        contact.setPhone("9966996699");
        contactDao.addContact(contact);
        System.out.println("Added contact with id - " + id + "\n contact - " + contactDao.findContactById(id));
*/

        // DELETE
/*        id = 55;
        contactDao.deleteContact(id);
        System.out.println("Deleted contact with id - " + id );
*/


    }
}