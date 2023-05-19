package com.example.Dosify.model;

import com.example.Dosify.Enum.VaccineType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dose_1 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String doseId;

    VaccineType vaccineType;

    @CreationTimestamp
    Date vaccinationDate;

    /*Now Established the Parent-child relationship*/

    @OneToOne
    @JoinColumn
    User user;
}
