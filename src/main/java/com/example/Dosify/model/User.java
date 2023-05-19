package com.example.Dosify.model;

import com.example.Dosify.Enum.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name="age")
    int age;

    @Column(name = "emil_ID", unique = true, nullable = false)
    String emailId;

    @Column(name = "mob_No", unique = true, nullable = false)
    String mobNo;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "is_dose1_Taken")
    boolean isDose1Taken;

    @Column(name = "is_dose2_Taken")
    boolean isDose2Taken;

    /*Now Established the Parent-child relationship*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Dose_1 dose_1;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Dose_2 dose_2;
}
