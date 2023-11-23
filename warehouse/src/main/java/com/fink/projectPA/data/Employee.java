/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectPA.data;

import java.sql.Date;

/**
 *
 * @author Djordje
 */
public class Employee {
    private int employeeId = -1;
    private String lastName;
    private String firstName;
    private Date birthDate;

    public Employee(int employeeId, String lastName, String firstName, Date birthDate) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }
    public Employee(){
        
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", lastName=" + lastName + ", firstName=" + firstName + ", birthDate=" + birthDate + '}';
    }
    
    
    
}
