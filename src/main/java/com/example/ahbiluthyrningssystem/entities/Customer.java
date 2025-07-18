package com.example.ahbiluthyrningssystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

//--------------------- Elham - class Customer --------------
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (length = 30, nullable = false)
    private String first_name;
    @Column (length = 30, nullable = false)
    private String last_name;
    @Column (length = 15, nullable = false, unique = true)
    private String personalnumber;
    @Column (length = 30, nullable = false)
    private String address;
    @Column (length = 50, nullable = false)
    private String email;
    @Column (length = 14, nullable = true)
    private String phone_number;
    @JsonIgnoreProperties("customer")
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders ;

    public Customer() {
    }

    public Customer(String first_name, String last_name, String personalnumber, String address, String email, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.personalnumber = personalnumber;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPersonalnumber() {
        return personalnumber;
    }

    public void setPersonalnumber(String personalnumber) {
        this.personalnumber = personalnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer[" +
                "customer_id:" + id +
                ", first_name:'" + first_name + '\'' +
                ", last_name:'" + last_name + '\'' +
                ", personal_number:'" + personalnumber + '\'' +
                ", address:'" + address + '\'' +
                ", email:'" + email + '\'' +
                ", phone_number:'" + phone_number + '\'' +
                ", order_id:" + orders +
                ']';
    }
}
//--------------------- Elham - class Customer --------------