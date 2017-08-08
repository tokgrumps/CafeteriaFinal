package com.example.a15017395.fyptestapp;

/**
 * Created by 15056233 on 20/6/2017.
 */

public class user {
        private int id;
        private  String firstName;
        private  String lastName;
        private  String username;
        private  String email;
        private int contactNo;
        private int roleID;
       // private String role;

    public user(){super();}


    public int getId() { return id; }

    public void setId(int id) {this.id = id;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) { this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName; }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public int getContactNo() {return contactNo;}

    public void setContactNo(int contactNo) {this.contactNo = contactNo;}

    public int getRoleID() {return roleID;}

    public void setRoleID(int role_id) { this.roleID = roleID;}

    //    public String getRole() {return role;}
//
//    public void setRole(String role) {this.role = role;}

    public String toString(){
        return getUsername();
    }
}
