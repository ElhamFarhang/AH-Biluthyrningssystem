package com.example.ahbiluthyrningssystem.entities;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    @Column (length = 30, nullable = false)
    private String first_name;
    @Column (length = 30, nullable = false)
    private String last_name;
    @Column (length = 15, nullable = false, unique = true)
    private String personal_number;
    @Column (length = 30, nullable = false)
    private String address;
    @Column (length = 50, nullable = false)
    private String email;
    @Column (length = 14, nullable = true)
    private String phone_number;
    @JoinColumn (name = "order_id", nullable = true)
    private Long order_id ;

    public Customer() {
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
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

    public String getPersonal_number() {
        return personal_number;
    }

    public void setPersonal_number(String personal_number) {
        this.personal_number = personal_number;
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

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Customer[" +
                "customer_id=" + customer_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", personal_number='" + personal_number + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", order_id=" + order_id +
                ']';
    }
}
