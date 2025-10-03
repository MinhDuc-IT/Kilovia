package com.example.kilovia_backend.controllers;

import com.example.kilovia_backend.dtos.requestDtos.CreateGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateGradeRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateGradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.GradeResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateGradeResponseDto;
import com.example.kilovia_backend.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/all")
    public List<GradeResponseDto> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @PostMapping("/add")
    public ResponseEntity<CreateGradeResponseDto> createGrade(@RequestBody CreateGradeRequestDto createGradeRequestDto) {
        CreateGradeResponseDto createdGrade = gradeService.createGrade(createGradeRequestDto);
        return ResponseEntity.ok(createdGrade);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateGradeResponseDto> updateGrade(@RequestBody UpdateGradeRequestDto updateGradeRequestDto) {
        UpdateGradeResponseDto updatedGrade = gradeService.updateGrade(updateGradeRequestDto);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/delete/{gradeId}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Integer gradeId) {
        gradeService.deleteGrade(gradeId);
        return ResponseEntity.noContent().build();
    }
}
