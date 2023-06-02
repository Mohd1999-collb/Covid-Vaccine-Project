package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.dto.RequestDto.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDto.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDto.CenterResponseDto;
import com.example.Dosify.exception.*;
import com.example.Dosify.model.*;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.AppointmentService;
import com.example.Dosify.service.CertificateService;
import com.example.Dosify.service.Dose_1Service;
import com.example.Dosify.service.Dose_2Service;
import com.example.Dosify.transformer.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    Dose_1Service dose_1Service;

    @Autowired
    Dose_2Service dose_2Service;

    @Autowired
    CertificateService certificateService;

    @Autowired
    private JavaMailSender emailSender;;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException, UserFullyVaccinatedException, VaccineTypeNotSameException {
        /*Check user exist or not*/
        Optional<User> userOpt = userRepository.findById(appointmentRequestDto.getUserId());
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("User doesn't exist.");
        }
        User user = userOpt.get();

        /*Check doctor exist or not*/
        Optional<Doctor> doctorOpt = doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if (doctorOpt.isEmpty()){
            throw new DoctorNotFoundException("Doctor doesn't exist.");
        }
        Doctor doctor = doctorOpt.get();

        /*Check user is fully vaccinated or not*/
        if (user.isDose1Taken() && user.isDose2Taken()){
            throw new UserFullyVaccinatedException("Sorry! User is already fully vaccinated.");
        }

        /*Check user first dose is taken or not*/
        if (appointmentRequestDto.getDoseNo() == DoseNo.Dose_1){
            if (user.isDose1Taken()){
            throw new UserFullyVaccinatedException("Sorry! User is already taken first dose.");
        }else {
                Dose_1 dose_1 = dose_1Service.createDose1(user, appointmentRequestDto.getVaccineType());
                user.setDose1Taken(true);
                user.setDose_1(dose_1);
            }
        }else{
            if (!user.isDose1Taken()){
                throw new NotEligibleForDoseException("Sorry! You are not yet eligible for Dose 2.");
            }else{
                /*Check user take both dose is same or not*/
                if (user.getDose_1().getVaccineType() != appointmentRequestDto.getVaccineType()){
                    throw new VaccineTypeNotSameException("Please! Enter the same vaccine name which is " +
                            "taken as Dose 1.");
                }
                Dose_2 dose_2 = dose_2Service.createDose2(user, appointmentRequestDto.getVaccineType());
                user.setDose2Taken(true);
                user.setDose_2(dose_2);
            }
        }

        /*Set the appointment parameter*/
        Appointment appointment = Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor)
                .vaccinationCenter(doctor.getVaccinationCenter())
                .build();

        user.getAppointments().add(appointment); // Update the user appointment list

        /*Saved into database*/
        User savedUser = userRepository.save(user); // Save both user and appointment

        Appointment savedAppointment = savedUser.getAppointments().get(savedUser.getAppointments().size() - 1);
        doctor.getAppointments().add(savedAppointment); // Update the doctor appointment list

        /*Saved into database*/
        Doctor savedDoctor = doctorRepository.save(doctor); // Save both doctor and appointment

        /*Create dose 1 certificate*/
        if (user.isDose1Taken() && !user.isDose2Taken()){
            dose1AppointmentMail(user.getName(),user.getEmailId(), savedAppointment.getId());
            certificateService.createDoseCertificate(user, DoseNo.Dose_1);
        }
        /*Create dose 1 certificate*/
        if (user.isDose1Taken() && user.isDose2Taken()){
            dose2AppointmentMail(user.getName(), user.getEmailId(), savedAppointment.getId());
            certificateService.createDoseCertificate(user, DoseNo.Dose_2);
        }

        /*Appointment(entity) --> Dto*/
        CenterResponseDto centerResponseDto =
        CenterTransformer.CenterToCenterResponseDto(doctor.getVaccinationCenter());
        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(savedAppointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .centerResponseDto(centerResponseDto)
                .doctorName(doctor.getName())
                .vaccineType(appointmentRequestDto.getVaccineType())
                .build();
    }

    /*Dose 1 appointment mail*/
    public void dose1AppointmentMail(String name, String emailId, int id) {
        String text =  name +
                " your 1st dose appointment is booked successfully and appointment number is " + id + ".\n" +
                "no-reply this is automated generated mail.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce7232@gmail.com");
        message.setTo(emailId);
        message.setSubject("Dosify COVID_19 Vaccination Center!!!");
        message.setText(text);
        emailSender.send(message);
    }

    public void dose2AppointmentMail(String name, String emailId, int id) {
        String text =  name +
                " your 2nd dose appointment is booked successfully and appointment number is " + id + ".\n" +
                "no-reply this is automated generated mail.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecommerce7232@gmail.com");
        message.setTo(emailId);
        message.setSubject("Dosify COVID_19 Vaccination Center!!!");
        message.setText(text);
        emailSender.send(message);
    }
}
