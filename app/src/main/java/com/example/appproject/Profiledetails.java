package com.example.appproject;

import java.io.Serializable;

public class Profiledetails implements Serializable {
    public String Uid;
    public String Uri;
    public String email;
    public String Name;
    public String Session;
    public  String Status;
    public String Gender;
    public String Age;
    public String Description;



    public Profiledetails()
    {

    }


    public Profiledetails(String Name,String Description, String session, String status, String gender, String age) {
        Session = session;
        //this.Uri=Uri;
        Status = status;
        Gender = gender;
        Age = age;
        this.Description=Description;
        this.Name=Name;
    }
}