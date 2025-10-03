package com.example.kilovia_backend.services;

import com.example.kilovia_backend.dtos.requestDtos.CreateGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateGradeRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.GradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateGradeResponseDto;

import java.util.List;

public interface GradeService {
    List<GradeResponseDto> getAllGrades();
    CreateGradeResponseDto createGrade(CreateGradeRequestDto createGradeRequestDto);
    UpdateGradeResponseDto updateGrade(UpdateGradeRequestDto updateGradeRequestDto);
    void deleteGrade(Integer gradeId);
}
