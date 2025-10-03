package com.example.kilovia_backend.dtos.responseDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto {
    private Integer id;
    private String subjectName;
}
