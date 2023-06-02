package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.exception.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException;

    List<String> doctorMoreThanAppointment();

    Float maleFemaleRatio();

    void updateDoctor(String emailId, String name, String mobNo) throws DoctorNotFoundException;
}
