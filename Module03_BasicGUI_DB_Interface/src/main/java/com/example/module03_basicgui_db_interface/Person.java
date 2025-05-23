package com.example.module03_basicgui_db_interface;

public class Person {


    private Integer id;
    private String firstName;
    private String lastName;
    private String dept;
    private String major;

    public Person() {
    }


    public Person( String firstName, String lastName, String dept, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.dept = dept;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


}