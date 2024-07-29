package com.record.student.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Student {
    
    @Id
    @Column(unique = true)
    @NotEmpty(message = "id is required !!")
    private String id;
    @NotEmpty(message = "Name Must not be Empty !!")
    @Size(min = 3, message = "name must be greater than or equal to 3 characters !!")
    private String name;
    @Email(message = "Enter Valid Email !!")
    @NotEmpty(message = "Email is required !!")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "Phone Number is required !!")
    private String phone;
    @NotEmpty(message = "Address is required !!")
    private String address;
    private String branch;
    private String forum;
    private double sgpa1;
    private double sgpa2;
    private double sgpa3;
    private double sgpa4;
    private double sgpa5;
    private double sgpa6;
    private double sgpa7;
    private double sgpa8;
    private double cgpa;
    private String backlog;
    private double attendance1;
    private double attendance2;
    private double attendance3;
    private double attendance4;
    private double attendance5;
    private double attendance6;
    private double attendance7;
    private double attendance8;
    private double avgAttendance;
    @NotEmpty(message = "Fill out the field !!")
    private String certification;
    @NotEmpty(message = "Fill out the field !!")
    private String participation;

}
