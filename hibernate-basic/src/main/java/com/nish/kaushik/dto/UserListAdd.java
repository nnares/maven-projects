package com.nish.kaushik.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "USER_LIST_ADD")
public class UserListAdd {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int id;
    private String name;
    private String phone;


//    @ElementCollection(fetch = FetchType.EAGER)
//    @ElementCollection(fetch = FetchType.LAZY)

    @ElementCollection // default table name "TableName_AttributeName" - USER_listOfAddresses
    @JoinTable(name="USER_ADDRESS", joinColumns = @JoinColumn(name="USER_ID"))
    private Set<Address> listOfAddresses =  new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Address> getListOfAddresses() {
        return listOfAddresses;
    }

    public void setListOfAddresses(Set<Address> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }

    @Override
    public String toString() {
        return "UserListAdd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", listOfAddresses=" + listOfAddresses +
                '}';
    }
}
