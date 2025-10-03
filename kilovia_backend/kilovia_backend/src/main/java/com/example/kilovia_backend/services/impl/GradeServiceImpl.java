package com.example.kilovia_backend.services.impl;

import com.example.kilovia_backend.dtos.requestDtos.CreateGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateGradeRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.GradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateGradeResponseDto;
import com.example.kilovia_backend.exceptions.exceptions.GradeNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.UpdateFailedException;
import com.example.kilovia_backend.mappers.GradeMapper;
import com.example.kilovia_backend.models.Grade;
import com.example.kilovia_backend.services.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeMapper gradeMapper;

    public GradeServiceImpl(GradeMapper gradeMapper) {
        this.gradeMapper = gradeMapper;
    }

    @Override
    public List<GradeResponseDto> getAllGrades() {
        return gradeMapper.getAllGrades();
    }

    @Override
    public CreateGradeResponseDto createGrade(CreateGradeRequestDto createGradeRequestDto) {
        Grade grade = new Grade();
        grade.setGradeName(createGradeRequestDto.getGradeName());
        gradeMapper.createGrade(grade);
        return new CreateGradeResponseDto(grade.getId(), grade.getGradeName());
    }

    @Override
    public UpdateGradeResponseDto updateGrade(UpdateGradeRequestDto updateGradeRequestDto) {
        Grade existGrade = gradeMapper.selectById(updateGradeRequestDto.getId());
        if (existGrade == null) {
            throw new GradeNotFoundException("Grade not found with id: " + updateGradeRequestDto.getId());
        }

        existGrade.setGradeName(updateGradeRequestDto.getGradeName());
        Integer rowsAffected = gradeMapper.updateGrade(existGrade);
        if (rowsAffected == 0) {
            throw new UpdateFailedException("Failed to update grade with id: " + updateGradeRequestDto.getId());
        }
        return new UpdateGradeResponseDto(
                existGrade.getId(),
                existGrade.getGradeName()
        );
    }

    @Override
    public void deleteGrade(Integer gradeId) {
        Grade existGrade = gradeMapper.selectById(gradeId);
        if (existGrade == null) {
            throw new GradeNotFoundException("Grade not found with id: " + gradeId);
        }
        Integer rowsAffected = gradeMapper.deleteGrade(gradeId);
        if (rowsAffected == 0) {
            throw new UpdateFailedException("Failed to delete grade with id: " + gradeId);
        }
    }
}
