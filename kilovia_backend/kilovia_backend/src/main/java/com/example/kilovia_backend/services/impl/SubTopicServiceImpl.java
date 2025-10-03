package com.example.kilovia_backend.services.impl;

import com.example.kilovia_backend.dtos.requestDtos.CreateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateSubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateSubTopicResponseDto;
import com.example.kilovia_backend.exceptions.exceptions.DeleteFailedException;
import com.example.kilovia_backend.exceptions.exceptions.TopicNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.UpdateFailedException;
import com.example.kilovia_backend.mappers.SubTopicMapper;
import com.example.kilovia_backend.mappers.TopicMapper;
import com.example.kilovia_backend.models.SubTopic;
import com.example.kilovia_backend.models.Topic;
import com.example.kilovia_backend.services.SubTopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTopicServiceImpl implements SubTopicService {

    private final SubTopicMapper subTopicMapper;
    private final TopicMapper topicMapper;

    public SubTopicServiceImpl(SubTopicMapper subTopicMapper, TopicMapper topicMapper) {
        this.subTopicMapper = subTopicMapper;
        this.topicMapper = topicMapper;
    }

    @Override
    public List<SubTopicResponseDto> getSubTopicsByTopicId(Integer topicId) {
        Topic topic = topicMapper.selectById(topicId);
        if (topic == null) {
            throw new TopicNotFoundException("Topic not found with id: " + topicId);
        }
        return subTopicMapper.getSubTopicsByTopicId(topicId);
    }

    @Override
    public CreateSubTopicResponseDto createSubTopic(CreateSubTopicRequestDto createSubTopicRequestDto) {
        Topic topic = topicMapper.selectById(createSubTopicRequestDto.getTopicId());
        if (topic == null) {
            throw new TopicNotFoundException("Topic not found with id: " + createSubTopicRequestDto.getTopicId());
        }

        SubTopic subTopic = new SubTopic();
        subTopic.setSubTopicTitle(createSubTopicRequestDto.getSubTopicTitle());
        subTopic.setTopicId(createSubTopicRequestDto.getTopicId());

        subTopicMapper.createSubTopic(subTopic);

        return new CreateSubTopicResponseDto(
                subTopic.getId(),
                subTopic.getSubTopicTitle(),
                subTopic.getTopicId()
        );
    }

    @Override
    public void deleteSubTopic(Integer subTopicId) {
        SubTopic existingSubTopic = subTopicMapper.selectById(subTopicId);
        if (existingSubTopic == null) {
            throw new TopicNotFoundException("SubTopic not found with id: " + subTopicId);
        }

        Integer rowsAffected = subTopicMapper.deleteSubTopic(subTopicId);
        if (rowsAffected == null || rowsAffected == 0) {
            throw new DeleteFailedException("Failed to delete SubTopic with id: " + subTopicId);
        }
    }

    @Override
    public UpdateSubTopicResponseDto updateSubTopic(UpdateSubTopicRequestDto updateSubTopicRequestDto) {
        SubTopic existingSubTopic = subTopicMapper.selectById(updateSubTopicRequestDto.getId());
        if (existingSubTopic == null) {
            throw new TopicNotFoundException("SubTopic not found with id: " + updateSubTopicRequestDto.getId());
        }

        existingSubTopic.setSubTopicTitle(updateSubTopicRequestDto.getSubTopicTitle());
        existingSubTopic.setTopicId(updateSubTopicRequestDto.getTopicId());

        Integer rowsAffected = subTopicMapper.updateSubTopic(existingSubTopic);
        if (rowsAffected == null || rowsAffected == 0) {
            throw new UpdateFailedException("Failed to update SubTopic with id: " + updateSubTopicRequestDto.getId());
        }

        return new UpdateSubTopicResponseDto(
                existingSubTopic.getId(),
                existingSubTopic.getSubTopicTitle(),
                existingSubTopic.getTopicId()
        );
    }
}
