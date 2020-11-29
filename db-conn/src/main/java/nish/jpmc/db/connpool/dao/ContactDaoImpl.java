package nish.jpmc.db.connpool.dao;

import nish.jpmc.db.connpool.model.Contact;
import nish.jpmc.db.connpool.model.ContactRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class ContactDaoImpl implements ContactDao {

    private JdbcTemplate jdbcTemplate;
    private String sqlGetAllContact;
    private String sqlFindContactById;
    private String sqlAddContact;
    private String sqlUpdateContact;
    private String sqlDeleteContact;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSqlGetAllContact() {
        return sqlGetAllContact;
    }

    public void setSqlGetAllContact(String sqlGetAllContact) {
        this.sqlGetAllContact = sqlGetAllContact;
    }

    public String getSqlFindContactById() {
        return sqlFindContactById;
    }

    public void setSqlFindContactById(String sqlFindContactById) {
        this.sqlFindContactById = sqlFindContactById;
    }

    public String getSqlAddContact() {
        return sqlAddContact;
    }

    public void setSqlAddContact(String sqlAddContact) {
        this.sqlAddContact = sqlAddContact;
    }

    public String getSqlUpdateContact() {
        return sqlUpdateContact;
    }

    public void setSqlUpdateContact(String sqlUpdateContact) {
        this.sqlUpdateContact = sqlUpdateContact;
    }

    public String getSqlDeleteContact() {
        return sqlDeleteContact;
    }

    public void setSqlDeleteContact(String sqlDeleteContact) {
        this.sqlDeleteContact = sqlDeleteContact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(getSqlGetAllContact(), new ContactRowMapper());
    }

    @Override
    public Contact findContactById(int id) {
        return jdbcTemplate.queryForObject(getSqlFindContactById(), new ContactRowMapper(), id);
    }

    @Override
    public void addContact(Contact contact) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate().getDataSource());

        SqlParameterSource sqlParameters = new MapSqlParameterSource("contactId", contact.getContactId())
                .addValue("name", contact.getName())
                .addValue("email", contact.getEmail())
                .addValue("address", contact.getAddress())
                .addValue("telephone", contact.getPhone());

        namedParameterJdbcTemplate.update(getSqlAddContact(), sqlParameters);
    }

    @Override
    public void updateContact(Contact contact) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate().getDataSource());

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
        sqlParameters.addValue("name", contact.getName());
        sqlParameters.addValue("email", contact.getEmail());
        sqlParameters.addValue("address", contact.getAddress());
        sqlParameters.addValue("telephone", contact.getPhone());
        sqlParameters.addValue("contactId", contact.getContactId());
        namedParameterJdbcTemplate.update(getSqlUpdateContact(), sqlParameters);
    }

    @Override
    public void deleteContact(int id) {
        jdbcTemplate.update(getSqlDeleteContact(), id);
    }


}