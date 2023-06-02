package com.example.Dosify.repository;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailId(String emailId);

    /*Sql Query*/
    @Query(value = "select name from user where is_dose1_taken = 0 and is_dose2_taken = 0", nativeQuery = true)
    List<String> getUserNotTakenEvenASingleDose();

    /*Sql Query*/
    @Query(value = "select name from user where is_dose1_taken = 1 and is_dose2_taken = 0", nativeQuery = true)
    List<String> getUserTakenDose1NotTakenDose2();

    /*Sql Query*/
//    @Query(value = "select name from User name where name.isDose1Taken = false and name.isDose2Taken = false and name.gender = :gender ", nativeQuery = false)
    @Query(value = "select name from user where is_dose1_taken = 0 and is_dose2_taken = 0 and gender = :male", nativeQuery = true)
    List<String> getMaleUserNotTakenEvenASingleDose(String male);

    /*Sql Query*/
//    @Query(value = "select name from User name where name.isDose1Taken = false and name.isDose2Taken = false and name.gender = :female ", nativeQuery = false)
    @Query(value = "select name from user where is_dose1_taken = 0 and is_dose2_taken = 0 and gender = :female", nativeQuery = true)
    List<String> getFemaleUserNotTakenEvenASingleDose(String female);

    /*Sql Query*/
    @Query(value = "select name from user where is_dose1_taken = 1 and is_dose2_taken = 1", nativeQuery = true)
    List<String> getAllUserFullyVaccinated();

    /*Sql Query*/
    @Query(value = "select name from user where is_dose1_taken = 1 and is_dose2_taken = 1 and gender = :male", nativeQuery = true)
    List<String> getAllMaleUserFullyVaccinated(String male);

    /*Sql Query*/
    @Query(value = "select name from user where is_dose1_taken = 1 and is_dose2_taken = 1 and gender = :female", nativeQuery = true)
    List<String> getAllFemaleUserFullyVaccinated(String female);
    }
