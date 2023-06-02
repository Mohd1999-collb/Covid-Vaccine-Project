package com.example.Dosify.service.implementServiceInterface;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose_2;
import com.example.Dosify.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose_2ServiceImp implements com.example.Dosify.service.Dose_2Service {
    @Override
    public Dose_2 createDose2(User user, VaccineType vaccineType) {
        return Dose_2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .user(user)
                .vaccineType(vaccineType)
                .build();
    }
}
