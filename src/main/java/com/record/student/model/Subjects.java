package com.record.student.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Subjects {

    @Id
    private int sem;

    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;

    @ManyToOne
    private Student student;

}
