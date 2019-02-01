package com.google.group.laagsugboapp;

class Users
{
    String fullname, email, address, contact, password, stationRelatedNo, status;
    public Users()
    {

    }
    public Users( String fullname, String email, String address, String contact, String password)
    {
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.contact = contact;
        this.password = password;
    }



    public String getStationRelatedNo() {
        return stationRelatedNo;
    }

    public String getStatus() {
        return status;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }
}
