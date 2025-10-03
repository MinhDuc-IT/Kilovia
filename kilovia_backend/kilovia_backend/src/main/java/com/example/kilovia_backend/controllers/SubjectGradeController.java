package com.example.kilovia_backend.controllers;

import com.example.kilovia_backend.dtos.requestDtos.CreateSubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.SubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateSubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateSubjectGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubjectGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubjectGradeWithTopicsResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateSubjectGradeResponseDto;
import com.example.kilovia_backend.services.SubjectGradeService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject-grades")
public class SubjectGradeController {
    private final SubjectGradeService subjectGradeService;

    public SubjectGradeController(SubjectGradeService subjectGradeService) {
        this.subjectGradeService = subjectGradeService;
    }

    @GetMapping("/topics-and-subtopics")
    public ResponseEntity<SubjectGradeWithTopicsResponseDto> getTopicsAndSubTopics(@RequestBody SubjectGradeRequestDto subjectGradeRequestDto) {
        SubjectGradeWithTopicsResponseDto subjectGradeResponseDto = subjectGradeService.getTopicsAndSubTopicsBySubjectGraded(subjectGradeRequestDto);
        return ResponseEntity.ok(subjectGradeResponseDto);
    }

    @GetMapping("/by-grade/{gradeId}")
    public ResponseEntity<List<SubjectGradeResponseDto>> getSubjectGradesByGrade(@PathVariable Integer gradeId) {
        List<SubjectGradeResponseDto> subjectGradeResponseDto = subjectGradeService.getAllSubjectGradesByGradeId(gradeId);
        return ResponseEntity.ok(subjectGradeResponseDto);
    }

    @GetMapping("by-subject/{subjectId}")
    public ResponseEntity<List<SubjectGradeResponseDto>> getSubjectGradesBySubject(@PathVariable Integer subjectId) {
        List<SubjectGradeResponseDto> subjectGradeResponseDto = subjectGradeService.getAllSubjectGradesBySubjectId(subjectId);
        return ResponseEntity.ok(subjectGradeResponseDto);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateSubjectGradeResponseDto> createSubjectGrade(@RequestBody CreateSubjectGradeRequestDto createSubjectGradeRequestDto) {
        CreateSubjectGradeResponseDto createdSubjectGrade = subjectGradeService.createSubjectGrade(createSubjectGradeRequestDto);
        return ResponseEntity.ok(createdSubjectGrade);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateSubjectGradeResponseDto> updateSubjectGrade(@RequestBody UpdateSubjectGradeRequestDto updateSubjectGradeRequestDto) {
        UpdateSubjectGradeResponseDto updatedSubjectGrade = subjectGradeService.updateSubjectGrade(updateSubjectGradeRequestDto);
        return ResponseEntity.ok(updatedSubjectGrade);
    }

    @DeleteMapping("/delete/{subjectGradeId}")
    public ResponseEntity<Void> deleteSubjectGrade(@PathVariable Integer subjectGradeId) {
        subjectGradeService.deleteSubjectGrade(subjectGradeId);
        return ResponseEntity.noContent().build();
    }
}
