package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDto.CenterRequestDto;
import com.example.Dosify.dto.ResponseDto.CenterResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    /*Add center*/
    @PostMapping("/add-center")
    public ResponseEntity<CenterResponseDto> addVaccinationCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto = vaccinationCenterService.addVaccinationCenter(centerRequestDto);
        return new ResponseEntity<>(centerResponseDto, HttpStatus.CREATED);
    }

    /* Give all centers of a particular centerType*/
    @GetMapping("/particular-center-type")
    public ResponseEntity<List<String>> particularCenterType(@RequestParam String centerType){
        return new ResponseEntity<>(vaccinationCenterService.particularCenterType(centerType), HttpStatus.FOUND);
    }

    /* Give the list of all doctors at a particular center(centerId) */
    @GetMapping("/doctor-at-particular-center")
    public ResponseEntity<Object> doctorAtParticularCenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationCenterService.doctorAtParticularCenter(centerId), HttpStatus.FOUND);
        }catch (CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Give the list of all male doctors at a particular center(centerId)
    @GetMapping("/male-doctor-at-particular-center")
    public ResponseEntity<Object> maleDoctorAtParticularCenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationCenterService.maleDoctorAtParticularCenter(centerId), HttpStatus.FOUND);
        }catch (CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Give the list of all females doctors at a particular center(centerId)
    @GetMapping("/female-doctor-at-particular-center")
    public ResponseEntity<Object> femaleDoctorAtParticularCenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationCenterService.femaleDoctorAtParticularCenter(centerId), HttpStatus.FOUND);
        }catch (CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Give the list of all male doctors above age 40 at a particular center(centerId)
    @GetMapping("/male-doctor-above-age-at-particular-center")
    public ResponseEntity<Object> maleDoctorAboveAgeAtParticularCenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationCenterService.maleDoctorAboveAgeAtParticularCenter(centerId), HttpStatus.FOUND);
        }catch (CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Give the list of all male doctors above age 40 at a particular center(centerId)
    @GetMapping("/female-doctor-above-age-at-particular-center")
    public ResponseEntity<Object> femaleDoctorAboveAgeAtParticularCenter(@RequestParam int centerId){
        try{
            return new ResponseEntity<>(vaccinationCenterService.femaleDoctorAboveAgeAtParticularCenter(centerId), HttpStatus.FOUND);
        }catch (CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
