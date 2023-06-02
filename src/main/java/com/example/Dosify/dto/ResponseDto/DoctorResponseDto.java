package com.example.Dosify.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorResponseDto {
    String name;

    String emailId;

    String mobNo;

    CenterResponseDto centerResponseDto;
}
