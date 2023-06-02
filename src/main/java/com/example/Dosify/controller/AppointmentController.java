package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;
import com.example.Dosify.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book-appointment")
    public ResponseEntity<Object> bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
            try{
                AppointmentResponseDto appointmentResponseDto = appointmentService.bookAppointment(appointmentRequestDto);
                return new ResponseEntity<Object>(appointmentResponseDto, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

    // generate certificate
    // -> One dose
    // -> Both dose
}
