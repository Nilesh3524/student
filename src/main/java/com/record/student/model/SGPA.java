package com.record.student.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SGPA {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double sgpa1;
    private double sgpa2;
    private double sgpa3;
    private double sgpa4;
    private double sgpa5;
    private double sgpa6;
    private double sgpa7;
    private double sgpa8;

    @ManyToOne
    private Student student;

}
