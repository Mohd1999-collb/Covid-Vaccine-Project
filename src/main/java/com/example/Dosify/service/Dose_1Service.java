package com.example.Dosify.service;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose_1;
import com.example.Dosify.model.User;

public interface Dose_1Service {
    public Dose_1 createDose1(User user, VaccineType vaccineType);
}
