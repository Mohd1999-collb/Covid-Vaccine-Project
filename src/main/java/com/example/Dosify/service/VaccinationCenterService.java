package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDto.CenterRequestDto;
import com.example.Dosify.dto.ResponseDto.CenterResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;

import java.util.List;

public interface VaccinationCenterService {
    public CenterResponseDto addVaccinationCenter(CenterRequestDto centerRequestDto);

    public List<String> particularCenterType(String centerType);

    public  List<List<String>> doctorAtParticularCenter(int centerId) throws CenterNotPresentException;

    public  List<String> maleDoctorAtParticularCenter(int centerId) throws CenterNotPresentException;

    public  List<String> femaleDoctorAtParticularCenter(int centerId) throws CenterNotPresentException;

    public  List<String> maleDoctorAboveAgeAtParticularCenter(int centerId) throws CenterNotPresentException;

    List<String> femaleDoctorAboveAgeAtParticularCenter(int centerId) throws CenterNotPresentException;
}
