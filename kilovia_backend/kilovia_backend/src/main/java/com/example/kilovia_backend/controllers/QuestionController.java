package com.example.kilovia_backend.controllers;

import com.example.kilovia_backend.dtos.requestDtos.CreateQuestionRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateQuestionRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateQuestionResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.QuestionResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateQuestionResponseDto;
import com.example.kilovia_backend.services.QuestionService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/by-subtopic/{subTopicId}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsBySubTopic(@PathVariable Integer subTopicId) {
        List<QuestionResponseDto> questions = questionService.getQuestionsBySubTopicId(subTopicId);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateQuestionResponseDto> createQuestion(@RequestBody CreateQuestionRequestDto createQuestionRequestDto) {
        CreateQuestionResponseDto createdQuestion = questionService.createQuestion(createQuestionRequestDto);
        return ResponseEntity.ok(createdQuestion);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateQuestionResponseDto> updateQuestion(@RequestBody UpdateQuestionRequestDto updateQuestionRequestDto) {
        UpdateQuestionResponseDto updatedQuestion = questionService.updateQuestion(updateQuestionRequestDto);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
