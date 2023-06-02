package com.example.Dosify.service;

import com.example.Dosify.Enum.VaccineType;
import com.example.Dosify.model.Dose_2;
import com.example.Dosify.model.User;

public interface Dose_2Service {
    public Dose_2 createDose2(User user, VaccineType vaccineType);
}
