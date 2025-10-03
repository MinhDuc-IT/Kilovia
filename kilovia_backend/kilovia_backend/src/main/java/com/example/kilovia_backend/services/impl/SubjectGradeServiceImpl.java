package com.example.kilovia_backend.services.impl;

import com.example.kilovia_backend.dtos.requestDtos.CreateSubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.SubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateSubjectGradeRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.*;
import com.example.kilovia_backend.exceptions.exceptions.DeleteFailedException;
import com.example.kilovia_backend.exceptions.exceptions.GradeNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.SubjectGradeNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.SubjectNotFoundException;
import com.example.kilovia_backend.mappers.*;
import com.example.kilovia_backend.models.Grade;
import com.example.kilovia_backend.models.Subject;
import com.example.kilovia_backend.models.SubjectGrade;
import com.example.kilovia_backend.services.SubjectGradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectGradeServiceImpl implements SubjectGradeService {

    private final SubjectGradeMapper subjectGradeMapper;
    private final TopicMapper topicMapper;
    private final SubTopicMapper subTopicMapper;
    private final GradeMapper gradeMapper;
    private final SubjectMapper subjectMapper;

    public SubjectGradeServiceImpl (SubjectGradeMapper subjectGradeMapper,
                                    TopicMapper topicMapper,
                                    SubTopicMapper subTopicMapper,
                                    GradeMapper gradeMapper,
                                    SubjectMapper subjectMapper) {
        this.subjectGradeMapper = subjectGradeMapper;
        this.topicMapper = topicMapper;
        this.subTopicMapper = subTopicMapper;
        this.gradeMapper = gradeMapper;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public SubjectGradeWithTopicsResponseDto getTopicsAndSubTopicsBySubjectGraded(SubjectGradeRequestDto subjectGradeRequestDto) {
        SubjectGrade subjectGrade = subjectGradeMapper.getSubjectGradesByGradeIdAndSubjectId(
                subjectGradeRequestDto.getGradeId(),
                subjectGradeRequestDto.getSubjectId()
        );
        if (subjectGrade == null) {
            throw new SubjectGradeNotFoundException("No subject grades found for the given "
                    + "gradeId: "+ subjectGradeRequestDto.getGradeId()
                    + ", subjectId " + subjectGradeRequestDto.getSubjectId());
        }
        SubjectGradeWithTopicsResponseDto subjectGradeResponseDto = new SubjectGradeWithTopicsResponseDto();
        subjectGradeResponseDto.setId(subjectGrade.getId());
        subjectGradeResponseDto.setSubjectGradeName(subjectGrade.getSubjectGradeName());

        List<TopicResponseDto> topics = topicMapper.getTopicsBySubjectGradeId(subjectGrade.getId());
        List<TopicWithSubTopicResponseDto> topicResponseDtos = topics.stream().map(topic -> {
            TopicWithSubTopicResponseDto topicResponseDto = new TopicWithSubTopicResponseDto();
            topicResponseDto.setId(topic.getId());
            topicResponseDto.setTopicTitle(topic.getTopicTitle());

            List<SubTopicResponseDto> subTopics = subTopicMapper.getSubTopicsByTopicId(topic.getId());
            List<SubTopicResponseDto> subTopicResponseDtos = subTopics.stream().map(subTopic -> {
                SubTopicResponseDto subTopicResponseDto = new SubTopicResponseDto();
                subTopicResponseDto.setId(subTopic.getId());
                subTopicResponseDto.setSubTopicTitle(subTopic.getSubTopicTitle());
                return subTopicResponseDto;
            }).toList();

            topicResponseDto.setSubTopics(subTopicResponseDtos);
            return topicResponseDto;
        }).toList();
        subjectGradeResponseDto.setTopics(topicResponseDtos);
        return subjectGradeResponseDto;
    }

    @Override
    public List<SubjectGradeResponseDto> getAllSubjectGradesByGradeId(int gradeId) {
        Grade existingGrade = gradeMapper.selectById(gradeId);
        if (existingGrade == null) {
            throw new GradeNotFoundException("Grade not found with id: " + gradeId);
        }
        List<SubjectGrade> subjectGrades = subjectGradeMapper.getAllSubjectGradesByGradeId(gradeId);
        return subjectGrades.stream().map(subjectGrade -> {
            SubjectGradeResponseDto subjectGradeResponseDto = new SubjectGradeResponseDto();
            subjectGradeResponseDto.setId(subjectGrade.getId());
            subjectGradeResponseDto.setSubjectGradeName(subjectGrade.getSubjectGradeName());
            subjectGradeResponseDto.setSubjectId(subjectGrade.getSubjectId());
            subjectGradeResponseDto.setGradeId(subjectGrade.getGradeId());
            return subjectGradeResponseDto;
        }).toList();
    }

    @Override
    public List<SubjectGradeResponseDto> getAllSubjectGradesBySubjectId(int subjectId) {
        Subject existingSubject = subjectMapper.selectById(subjectId);
        if (existingSubject == null) {
            throw new SubjectNotFoundException("Subject not found with id: " + subjectId);
        }

        List<SubjectGrade> subjectGrades = subjectGradeMapper.getAllSubjectGradesBySubjectId(subjectId);
        return subjectGrades.stream().map(subjectGrade -> {
            SubjectGradeResponseDto subjectGradeResponseDto = new SubjectGradeResponseDto();
            subjectGradeResponseDto.setId(subjectGrade.getId());
            subjectGradeResponseDto.setSubjectGradeName(subjectGrade.getSubjectGradeName());
            subjectGradeResponseDto.setSubjectId(subjectGrade.getSubjectId());
            subjectGradeResponseDto.setGradeId(subjectGrade.getGradeId());
            return subjectGradeResponseDto;
        }).toList();
    }

    @Override
    public CreateSubjectGradeResponseDto createSubjectGrade(CreateSubjectGradeRequestDto createSubjectGradeRequestDto){
        Subject existingSubject = subjectMapper.selectById(createSubjectGradeRequestDto.getSubjectId());
        if (existingSubject == null) {
            throw new SubjectNotFoundException("Subject not found with id: " + createSubjectGradeRequestDto.getSubjectId());
        }
        Grade existingGrade = gradeMapper.selectById(createSubjectGradeRequestDto.getGradeId());
        if (existingGrade == null) {
            throw new GradeNotFoundException("Grade not found with id: " + createSubjectGradeRequestDto.getGradeId());
        }
        SubjectGrade subjectGrade = new SubjectGrade();
        subjectGrade.setSubjectGradeName(createSubjectGradeRequestDto.getSubjectGradeName());
        subjectGrade.setSubjectId(createSubjectGradeRequestDto.getSubjectId());
        subjectGrade.setGradeId(createSubjectGradeRequestDto.getGradeId());
        subjectGradeMapper.insert(subjectGrade);

        CreateSubjectGradeResponseDto createSubjectGradeResponseDto = new CreateSubjectGradeResponseDto();
        createSubjectGradeResponseDto.setId(subjectGrade.getId());
        createSubjectGradeResponseDto.setSubjectGradeName(subjectGrade.getSubjectGradeName());
        createSubjectGradeResponseDto.setSubjectId(subjectGrade.getSubjectId());
        createSubjectGradeResponseDto.setGradeId(subjectGrade.getGradeId());

        return createSubjectGradeResponseDto;
    }

    @Override
    public UpdateSubjectGradeResponseDto updateSubjectGrade(UpdateSubjectGradeRequestDto updateSubjectGradeRequestDto){
        SubjectGrade existingSubjectGrade = subjectGradeMapper.selectById(updateSubjectGradeRequestDto.getId());
        if (existingSubjectGrade == null) {
            throw new SubjectGradeNotFoundException("Subject Grade not found with id: " + updateSubjectGradeRequestDto.getId());
        }
        Subject existingSubject = subjectMapper.selectById(updateSubjectGradeRequestDto.getSubjectId());
        if (existingSubject == null) {
            throw new SubjectNotFoundException("Subject not found with id: " + updateSubjectGradeRequestDto.getSubjectId());
        }
        Grade existingGrade = gradeMapper.selectById(updateSubjectGradeRequestDto.getGradeId());
        if (existingGrade == null) {
            throw new GradeNotFoundException("Grade not found with id: " + updateSubjectGradeRequestDto.getGradeId());
        }

        existingSubjectGrade.setSubjectGradeName(updateSubjectGradeRequestDto.getSubjectGradeName());
        existingSubjectGrade.setSubjectId(updateSubjectGradeRequestDto.getSubjectId());
        existingSubjectGrade.setGradeId(updateSubjectGradeRequestDto.getGradeId());
        subjectGradeMapper.updateById(existingSubjectGrade);

        UpdateSubjectGradeResponseDto updateSubjectGradeResponseDto = new UpdateSubjectGradeResponseDto();
        updateSubjectGradeResponseDto.setId(existingSubjectGrade.getId());
        updateSubjectGradeResponseDto.setSubjectGradeName(existingSubjectGrade.getSubjectGradeName());
        updateSubjectGradeResponseDto.setSubjectId(existingSubjectGrade.getSubjectId());
        updateSubjectGradeResponseDto.setGradeId(existingSubjectGrade.getGradeId());

        return updateSubjectGradeResponseDto;
    }

    @Override
    public void deleteSubjectGrade(int subjectGradeId) {
        SubjectGrade existingSubjectGrade = subjectGradeMapper.selectById(subjectGradeId);
        if (existingSubjectGrade == null) {
            throw new SubjectGradeNotFoundException("Subject Grade not found with id: " + subjectGradeId);
        }
        Integer rowAffected = subjectGradeMapper.deleteById(subjectGradeId);
        if (rowAffected == 0) {
            throw new DeleteFailedException("Failed to delete Subject Grade with id: " + subjectGradeId);
        }
    }

}
