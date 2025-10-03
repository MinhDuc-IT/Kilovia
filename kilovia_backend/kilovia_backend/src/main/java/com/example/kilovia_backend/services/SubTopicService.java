package com.example.kilovia_backend.services;

import com.example.kilovia_backend.dtos.requestDtos.CreateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateSubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateSubTopicResponseDto;

import java.util.List;

public interface SubTopicService {
    List<SubTopicResponseDto> getSubTopicsByTopicId(Integer topicId);
    CreateSubTopicResponseDto createSubTopic(CreateSubTopicRequestDto createSubTopicRequestDto);
    void deleteSubTopic(Integer subTopicId);
    UpdateSubTopicResponseDto updateSubTopic(UpdateSubTopicRequestDto updateSubTopicRequestDto);
}
