package com.example.kilovia_backend.services;

import com.example.kilovia_backend.dtos.requestDtos.CreateTopicRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.TopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateTopicResponseDto;

import java.util.List;

public interface TopicService {
    List<TopicResponseDto> getTopicsBySubjectGradeId(Integer subjectGradeId);
    CreateTopicResponseDto createTopic(CreateTopicRequestDto createTopicRequestDto);
    UpdateTopicResponseDto updateTopic(UpdateTopicRequestDto updateTopicRequestDto);
    void deleteTopic(Integer topicId);
}
