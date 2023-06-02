package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose_1;
import com.example.Dosify.model.User;
import com.example.Dosify.service.Dose_1Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose_1ServiceImp implements Dose_1Service {
    @Override
    public Dose_1 createDose1(User user, VaccineType vaccineType) {

        return Dose_1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();
    }
}
