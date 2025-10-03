package com.example.kilovia_backend.services;

import com.example.kilovia_backend.dtos.requestDtos.CreateSubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.SubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateSubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateSubjectGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubjectGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubjectGradeWithTopicsResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateSubjectGradeResponseDto;

import java.util.List;

public interface SubjectGradeService {
    SubjectGradeWithTopicsResponseDto getTopicsAndSubTopicsBySubjectGraded(SubjectGradeRequestDto subjectGradeRequestDto);
    List<SubjectGradeResponseDto> getAllSubjectGradesByGradeId(int gradeId);
    List<SubjectGradeResponseDto> getAllSubjectGradesBySubjectId(int subjectId);
    CreateSubjectGradeResponseDto createSubjectGrade(CreateSubjectGradeRequestDto createSubjectGradeRequestDto);
    UpdateSubjectGradeResponseDto updateSubjectGrade(UpdateSubjectGradeRequestDto updateSubjectGradeRequestDto);
    void deleteSubjectGrade(int subjectGradeId);
}
