package com.record.student.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student implements UserDetails {

    @Id
    @Column(unique = true)
    private String rollNo;

    private String name;

    private String branch;

    private String address;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private boolean forum;

    private boolean backlog;

    private String backSub;

    private String currentBacklog;

    private String cetification;

    private String participation;

    private double avgSGPA;

    private double avgAttendence;

    private String role;

    // subjects
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    private List<Subjects> subjects=new ArrayList<>();

    //attendence
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    private List<Attendence> attendence=new ArrayList<>();


    //sgpa
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    private List<SGPA> sgpa=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("STUDENT"));
    }

    @Override
    public String getUsername() {
        return getRollNo();
    }
}
