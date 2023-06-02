package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDto.CenterRequestDto;
import com.example.Dosify.dto.ResponseDto.CenterResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.VaccinationCenterRepository;
import com.example.Dosify.service.VaccinationCenterService;
import com.example.Dosify.transformer.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationCenterServiceImp implements VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Override
    public CenterResponseDto addVaccinationCenter(CenterRequestDto centerRequestDto) {
        /*DTO --> Entity*/
        VaccinationCenter vaccinationCenter = CenterTransformer.CenterRequestDtoToCenter(centerRequestDto);

        /*Save into database*/
//        VaccinationCenter savedCenter = vaccinationCenterRepository.save(vaccinationCenter);
        vaccinationCenterRepository.save(vaccinationCenter);

        /*Entity --> DTO*/
//        return CenterTransformer.CenterToCenterResponseDto(savedCenter);
        return CenterTransformer.CenterToCenterResponseDto(vaccinationCenter);
    }

    @Override
    public List<String> particularCenterType(String centerType) {
        return vaccinationCenterRepository.particularCenterType(centerType);
    }

    @Override
    public  List<List<String>> doctorAtParticularCenter(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOpt = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOpt.isEmpty()){
            throw new CenterNotPresentException("Invalid Center Id");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenterOpt.get();

        List<List<String>> doctorList = new ArrayList<>();
        List<Doctor> doctors =  vaccinationCenter.getDoctors();
        for (Doctor doctor : doctors) {
            List<String> ans = new ArrayList<>();
            if (doctor.getVaccinationCenter().getId() == centerId){
                ans.add(doctor.getName());
                ans.add(doctor.getVaccinationCenter().getName());
            }
            doctorList.add(ans);
        }
        return doctorList;
    }

    @Override
    public List<String> maleDoctorAtParticularCenter(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOpt = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOpt.isEmpty()){
            throw new CenterNotPresentException("Invalid Center Id");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenterOpt.get();
        List<String> doctorList = new ArrayList<>();
        List<Doctor> doctors =  vaccinationCenter.getDoctors();
        for (Doctor doctor : doctors) {
            Gender gender = doctor.getGender();
            String gen = gender.toString();

            if (doctor.getVaccinationCenter().getId() == centerId && gen.equals("MALE")){
                doctorList.add(doctor.getName());
            }
        }
        return doctorList;
    }

    @Override
    public List<String> femaleDoctorAtParticularCenter(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOpt = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOpt.isEmpty()){
            throw new CenterNotPresentException("Invalid Center Id");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenterOpt.get();
        List<String> doctorList = new ArrayList<>();
        List<Doctor> doctors =  vaccinationCenter.getDoctors();
        for (Doctor doctor : doctors) {
            Gender gender = doctor.getGender();
            String gen = gender.toString();

            if (doctor.getVaccinationCenter().getId() == centerId && gen.equals("FEMALE")){
                doctorList.add(doctor.getName());
            }
        }
        return doctorList;
    }

    @Override
    public List<String> maleDoctorAboveAgeAtParticularCenter(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOpt = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOpt.isEmpty()){
            throw new CenterNotPresentException("Invalid Center Id");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenterOpt.get();
        List<String> doctorList = new ArrayList<>();
        List<Doctor> doctors =  vaccinationCenter.getDoctors();
        for (Doctor doctor : doctors) {
            Gender gender = doctor.getGender();
            String gen = gender.toString();
            if (doctor.getVaccinationCenter().getId() == centerId && gen.equals("MALE") && doctor.getAge() > 40){
                doctorList.add(doctor.getName());
            }
        }
        return doctorList;
    }

    @Override
    public List<String> femaleDoctorAboveAgeAtParticularCenter(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOpt = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOpt.isEmpty()){
            throw new CenterNotPresentException("Invalid Center Id");
        }
        VaccinationCenter vaccinationCenter = vaccinationCenterOpt.get();
        List<String> doctorList = new ArrayList<>();
        List<Doctor> doctors =  vaccinationCenter.getDoctors();
        for (Doctor doctor : doctors) {
            Gender gender = doctor.getGender();
            String gen = gender.toString();
            if (doctor.getVaccinationCenter().getId() == centerId && gen.equals("FEMALE") && doctor.getAge() > 40){
                doctorList.add(doctor.getName());
            }
        }
        return doctorList;
    }
}
