package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add-doctor")
    public ResponseEntity<Object> addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity<Object>(doctorResponseDto,HttpStatus.CREATED);
        } catch (CenterNotPresentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /* Get all the doctors who have more than 10 appointment */
    @GetMapping("/doctor-more-than-appointment")
    public ResponseEntity<List<String>> doctorMoreThanAppointment(){
        return new ResponseEntity<>(doctorService.doctorMoreThanAppointment(), HttpStatus.FOUND);
    }

    /* Get the ratio of male to female doctors */
    @GetMapping("/male-female-ratio")
    public ResponseEntity<Float> maleFemaleRatio(){
        return new ResponseEntity<>(doctorService.maleFemaleRatio(), HttpStatus.CREATED);
    }

    /* Update the details based on email id of the doctor */
    @PutMapping("/update-doctor")
    public ResponseEntity<String> updateDoctor(@RequestParam String emailId, String name, String mobNo){
        try{
            doctorService.updateDoctor(emailId, name, mobNo);
            return new ResponseEntity<>("Doctor name and mobile number updated successfully.", HttpStatus.CREATED);
        }catch (DoctorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
