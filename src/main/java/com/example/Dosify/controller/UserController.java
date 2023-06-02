package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDto.UserRequestDto;
import com.example.Dosify.dto.ResponseDto.UserResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*Add user*/
    @PostMapping("/add-user")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto){
        return userService.addUser(userRequestDto);
    }

    /*Update the username and  mobno*/
    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestParam String emailId, String name, String mobNo){
        try{
            userService.updateUser(emailId, name, mobNo);
            return new ResponseEntity<>("User name and mobile number updated successfully.", HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /* All the users who have not taken even a single dose */
    @GetMapping("/user-not-taken-even-a-single-dose")
    public ResponseEntity<List<String>> userNotTakenEvenASingleDose(){
        return new ResponseEntity<>(userService.userNotTakenEvenASingleDose(), HttpStatus.FOUND);
    }

    /* All male users who have not taken even a single dose */
    @GetMapping("/male-user-not-taken-even-a-single-dose/{male}")
    public ResponseEntity<List<String>> maleUserNotTakenEvenASingleDose(@PathVariable String male){
        return new ResponseEntity<>(userService.maleUserNotTakenEvenASingleDose(male), HttpStatus.FOUND);
    }

    /* All female users who have not taken even a single dose */
    @GetMapping("/female-user-not-taken-even-a-single-dose/{female}")
    public ResponseEntity<List<String>> femaleUserNotTakenEvenASingleDose(@PathVariable String female){
        return new ResponseEntity<>(userService.femaleUserNotTakenEvenASingleDose(female), HttpStatus.FOUND);
    }

    /* All users who have taken does1 but not dose2*/
    @GetMapping("/user-taken-dose1-not-taken-dose2")
    public ResponseEntity<List<String>> userTakenDose1NotTakenDose2(){
        return new ResponseEntity<>(userService.userTakenDose1NotTakenDose2(), HttpStatus.FOUND);
    }

    /* All users who are fully vaccinated.*/
    @GetMapping("/user-fully-vaccinated")
    public ResponseEntity<List<String>> allUserFullyVaccinated(){
        return new ResponseEntity<>(userService.allUserFullyVaccinated(), HttpStatus.FOUND);
    }

    /* All male users who are fully vaccinated.*/
    @GetMapping("/male-user-fully-vaccinated/{male}")
    public ResponseEntity<List<String>> allMaleUserFullyVaccinated(@PathVariable String male){
        return new ResponseEntity<>(userService.allMaleUserFullyVaccinated(male), HttpStatus.FOUND);
    }

    /* All female users who are fully vaccinated.*/
    @GetMapping("/female-user-fully-vaccinated/{female}")
    public ResponseEntity<List<String>> allFemaleUserFullyVaccinated(@PathVariable String female){
        return new ResponseEntity<>(userService.allFemaleUserFullyVaccinated(female), HttpStatus.FOUND);
    }

    /*Delete user*/
    @DeleteMapping("delete-user")
    public String deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return "User deleted successfully.";
    }
}
