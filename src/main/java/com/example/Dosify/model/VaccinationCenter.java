package com.example.Dosify.model;

import com.example.Dosify.Enum.CenterType;
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
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String location;

    @Enumerated(EnumType.STRING)
    CenterType centerType;

    /*Now Established the Parent-child relationship*/

    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "vaccinationCenter", cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();
}
