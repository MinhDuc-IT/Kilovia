package com.example.kilovia_backend.dtos.responseDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubjectGradeResponseDto {
    private Integer id;
    private String subjectGradeName;
    private Integer subjectId;
    private Integer gradeId;
}
