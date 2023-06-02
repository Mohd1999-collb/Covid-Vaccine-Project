package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.CenterResponseDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.model.Doctor;

public class DoctorTransformer {
    public static Doctor DoctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .emailId(doctorRequestDto.getEmailId())
                .mobNo(doctorRequestDto.getMobNo())
                .gender(doctorRequestDto.getGender())
                .build();
    }

    public static DoctorResponseDto DoctorToDoctorResponseDto(Doctor doctor){
        CenterResponseDto centerResponseDto = CenterTransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter());
        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .emailId(doctor.getEmailId())
                .mobNo(doctor.getMobNo())
                .centerResponseDto(centerResponseDto)
                .build();
    }
}
