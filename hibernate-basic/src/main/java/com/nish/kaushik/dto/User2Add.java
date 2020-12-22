package com.nish.kaushik.dto;

import javax.persistence.*;

//@Table(name="USER")
@Entity(name = "USER_2_ADD")
public class User2Add {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String phone;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column = @Column(name="HOME_STREET_NAME")),
            @AttributeOverride(name="city", column = @Column(name="HOME_CITY_NAME")),
            @AttributeOverride(name="state", column = @Column(name="HOME_STATE_NAME")),
            @AttributeOverride(name="pinCode", column = @Column(name="HOME_PIN_CODE"))
    })
    private Address homeAddress;
    // Else - Exception in thread "main" org.hibernate.MappingException: Repeated column in mapping for entity: com.nish.kaushik.dto.User1 column: CITY_NAME (should be mapped with insert="false" update="false")

    @Embedded
    private Address officeAddress;

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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    @Override
    public String toString() {
        return "User2Add{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", homeAddress=" + homeAddress +
                ", officeAddress=" + officeAddress +
                '}';
    }
}
