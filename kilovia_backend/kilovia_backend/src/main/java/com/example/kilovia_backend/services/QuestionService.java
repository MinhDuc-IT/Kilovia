package com.example.kilovia_backend.services;

import com.example.kilovia_backend.dtos.requestDtos.CreateQuestionRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateQuestionRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateQuestionResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.QuestionResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateQuestionResponseDto;

import java.util.List;

public interface QuestionService {
    List<QuestionResponseDto> getQuestionsBySubTopicId(Integer subTopicId);
    CreateQuestionResponseDto createQuestion(CreateQuestionRequestDto createQuestionRequestDto);
    UpdateQuestionResponseDto updateQuestion(UpdateQuestionRequestDto updateQuestionRequestDto);
    void deleteQuestion(Integer questionId);
}
