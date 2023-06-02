package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDto.CertificateRequestDto;
import com.example.Dosify.dto.ResponseDto.CertificateResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @PostMapping("add-certificate")
    public ResponseEntity<Object> addCertificate(@RequestBody CertificateRequestDto certificateRequestDto){
        try{
            CertificateResponseDto certificateResponseDto = certificateService.addCertificate(certificateRequestDto);
            return new ResponseEntity<>(certificateResponseDto, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*Get the certificate  through mobile number*/
    @GetMapping("/get-certificate")
    public ResponseEntity<Object> getCertificate(@RequestParam String mobNo, String doseNo){
        try{
            CertificateResponseDto certificateResponseDto = certificateService.getCertificate(mobNo, doseNo);
            return new ResponseEntity<>(certificateResponseDto, HttpStatus.FOUND);
        }catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
