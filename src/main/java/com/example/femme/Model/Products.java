package com.example.femme.Model;

public class Products
{

    private String business_name, address, category, bid, contact_name, date, discription, email;
    private String phone_number1, phone_number2;

    public Products()
    {

    }

    public Products(String business_name, String address, String category, String bid, String contact_name, String date, String discription, String email, String phone_number1, String phone_number2) {
        this.business_name = business_name;
        this.address = address;
        this.category = category;
        this.bid = bid;
        this.contact_name = contact_name;
        this.date = date;
        this.discription = discription;
        this.email = email;
        this.phone_number1 = phone_number1;
        this.phone_number2 = phone_number2;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number1() {
        return phone_number1;
    }

    public void setPhone_number1(String phone_number1) {
        this.phone_number1 = phone_number1;
    }

    public String getPhone_number2() {
        return phone_number2;
    }

    public void setPhone_number2(String phone_number2) {
        this.phone_number2 = phone_number2;
    }
}
