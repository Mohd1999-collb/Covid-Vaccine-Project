package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.dto.RequestDto.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDto.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.VaccinationCenterRepository;
import com.example.Dosify.service.DoctorService;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImp implements DoctorService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {

        /*Check center is present or not*/
        Optional<VaccinationCenter> optionalCenter = vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());
        if (optionalCenter.isEmpty()){
            throw new CenterNotPresentException("Invalid Center Id");
        }

        VaccinationCenter vaccinationCenter = optionalCenter.get();

        /*DTO --> Entity*/
        Doctor doctor = DoctorTransformer.DoctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(vaccinationCenter);

        // add doctor to current list of doctors at that center
        vaccinationCenter.getDoctors().add(doctor);

        /*Saved into database*/
        vaccinationCenterRepository.save(vaccinationCenter); /*Saved both center and doctor*/

        /*Entity to DTO */
        return DoctorTransformer.DoctorToDoctorResponseDto(doctor);
    }

    @Override
    public List<String> doctorMoreThanAppointment(){
        Iterable<VaccinationCenter> vaccinationCenter = vaccinationCenterRepository.findAll();

        HashMap<String, Integer> hm = new HashMap<>();
        for (VaccinationCenter center: vaccinationCenter) {
            for (Appointment appointment:center.getAppointments()) {
                String name = appointment.getDoctor().getName();
                hm.put(name, hm.getOrDefault(name, 0) + 1);
            }
        }

        List<String> doctorName = new ArrayList<>();
        for (String key: hm.keySet()) {
            if (hm.get(key) >= 10){
                doctorName.add(key);
            }
        }
        return doctorName;
    }

    @Override
    public Float maleFemaleRatio() {
        Iterable<Doctor> doctorList = doctorRepository.findAll();
        float maleCount = 0, femaleCount = 0;
        for (Doctor doctor: doctorList) {
            if (doctor.getGender().toString().equals("MALE")){
                maleCount++;
            }else{
                femaleCount++;
            }
        }
        return maleCount / femaleCount;
    }

    @Override
    public void updateDoctor(String emailId, String name, String mobNo) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
        if (doctor == null){
            throw new DoctorNotFoundException("Doctor doesn't exist.");
        }
        doctor.setName(name);
        doctor.setMobNo(mobNo);
        doctorRepository.save(doctor);
    }
}
