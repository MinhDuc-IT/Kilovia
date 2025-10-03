package com.example.kilovia_backend.services.impl;

import com.example.kilovia_backend.dtos.requestDtos.CreateTopicRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.TopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateTopicResponseDto;
import com.example.kilovia_backend.exceptions.exceptions.DeleteFailedException;
import com.example.kilovia_backend.exceptions.exceptions.SubjectGradeNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.TopicNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.UpdateFailedException;
import com.example.kilovia_backend.mappers.SubjectGradeMapper;
import com.example.kilovia_backend.mappers.TopicMapper;
import com.example.kilovia_backend.models.SubjectGrade;
import com.example.kilovia_backend.models.Topic;
import com.example.kilovia_backend.services.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicMapper topicMapper;
    private final SubjectGradeMapper subjectGradeMapper;

    public TopicServiceImpl(TopicMapper topicMapper, SubjectGradeMapper subjectGradeMapper) {
        this.topicMapper = topicMapper;
        this.subjectGradeMapper = subjectGradeMapper;
    }

    @Override
    public List<TopicResponseDto> getTopicsBySubjectGradeId(Integer subjectGradeId) {
        return topicMapper.getTopicsBySubjectGradeId(subjectGradeId);
    }

    @Override
    public CreateTopicResponseDto createTopic(CreateTopicRequestDto createTopicRequestDto) {
        SubjectGrade existingSubjectGrade = subjectGradeMapper.selectById(createTopicRequestDto.getSubjectGradeId());
        if (existingSubjectGrade == null) {
            throw new SubjectGradeNotFoundException("Subject Grade not found with id: " + createTopicRequestDto.getSubjectGradeId());
        }

        Topic newTopic = new Topic();
        newTopic.setTopicTitle(createTopicRequestDto.getTopicTitle());
        newTopic.setSubjectGradeId(createTopicRequestDto.getSubjectGradeId());

        topicMapper.createTopic(newTopic);

        return new CreateTopicResponseDto(
                newTopic.getId(),
                newTopic.getTopicTitle(),
                newTopic.getSubjectGradeId()
        );
    }

    @Override
    public UpdateTopicResponseDto updateTopic(UpdateTopicRequestDto updateTopicRequestDto) {
        Topic existingTopic = topicMapper.selectById(updateTopicRequestDto.getId());
        if (existingTopic == null) {
            throw new TopicNotFoundException("Topic not found with id: " + updateTopicRequestDto.getId());
        }

        SubjectGrade existingSubjectGrade = subjectGradeMapper.selectById(updateTopicRequestDto.getSubjectGradeId());
        if (existingSubjectGrade == null) {
            throw new SubjectGradeNotFoundException("Subject Grade not found with id: " + updateTopicRequestDto.getSubjectGradeId());
        }

        Topic topicToUpdate = new Topic();
        topicToUpdate.setId(updateTopicRequestDto.getId());
        topicToUpdate.setTopicTitle(updateTopicRequestDto.getTopicTitle());
        topicToUpdate.setSubjectGradeId(updateTopicRequestDto.getSubjectGradeId());

        Integer rowsAffected = topicMapper.updateTopic(topicToUpdate);
        if (rowsAffected == null || rowsAffected == 0) {
            throw new UpdateFailedException("Failed to update topic with id: " + updateTopicRequestDto.getId());
        }

        return new UpdateTopicResponseDto(
                topicToUpdate.getId(),
                topicToUpdate.getTopicTitle(),
                topicToUpdate.getSubjectGradeId()
        );
    }

    @Override
    public void deleteTopic(Integer topicId) {
        Topic existingTopic = topicMapper.selectById(topicId);
        if (existingTopic == null) {
            throw new TopicNotFoundException("Topic not found with id: " + topicId);
        }
        Integer rowsAffected = topicMapper.deleteTopic(topicId);
        if (rowsAffected == null || rowsAffected == 0) {
            throw new DeleteFailedException("Failed to delete topic with id: " + topicId);
        }
    }
}
