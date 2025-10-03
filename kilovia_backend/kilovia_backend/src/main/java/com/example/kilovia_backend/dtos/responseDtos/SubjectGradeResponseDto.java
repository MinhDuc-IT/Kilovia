package com.example.kilovia_backend.dtos.responseDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectGradeResponseDto {
    private Integer id;
    private String subjectGradeName;
    private Integer gradeId;
    private Integer subjectId;
}
