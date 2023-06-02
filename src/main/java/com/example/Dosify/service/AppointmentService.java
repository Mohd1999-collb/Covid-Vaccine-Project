package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;
import com.example.Dosify.exception.*;

public interface AppointmentService {
    AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException, UserFullyVaccinatedException, VaccineTypeNotSameException;
}
