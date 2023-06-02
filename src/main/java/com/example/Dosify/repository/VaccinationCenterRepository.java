package com.example.Dosify.repository;

import com.example.Dosify.model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {

    /*Sql query*/
    @Query(value = "select name from vaccination_center where center_type = :centerType", nativeQuery = true)
    List<String> particularCenterType(String centerType);
}
