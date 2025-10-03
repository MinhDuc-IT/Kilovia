package com.example.kilovia_backend.services.impl;

import com.example.kilovia_backend.dtos.requestDtos.CreateQuestionRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateQuestionRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateQuestionResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.QuestionResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateQuestionResponseDto;
import com.example.kilovia_backend.exceptions.exceptions.QuestionNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.QuestionTypeNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.SubTopicNotFoundException;
import com.example.kilovia_backend.exceptions.exceptions.UpdateFailedException;
import com.example.kilovia_backend.mappers.QuestionMapper;
import com.example.kilovia_backend.mappers.QuestionTypeMapper;
import com.example.kilovia_backend.mappers.SubTopicMapper;
import com.example.kilovia_backend.models.Question;
import com.example.kilovia_backend.models.QuestionType;
import com.example.kilovia_backend.models.SubTopic;
import com.example.kilovia_backend.services.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;
    private final SubTopicMapper subTopicMapper;
    private final QuestionTypeMapper questionTypeMapper;

    public QuestionServiceImpl(QuestionMapper questionMapper, SubTopicMapper subTopicMapper, QuestionTypeMapper questionTypeMapper) {
        this.questionMapper = questionMapper;
        this.subTopicMapper = subTopicMapper;
        this.questionTypeMapper = questionTypeMapper;
    }

    @Override
    public List<QuestionResponseDto> getQuestionsBySubTopicId(Integer subTopicId) {
        List<Question> questions = questionMapper.getQuestionsBySubTopicId(subTopicId);

        if (questions.isEmpty()) {
            throw new QuestionNotFoundException("No questions found for subTopicId: " + subTopicId);
        }

        return questions.stream()
                .map(question -> new QuestionResponseDto(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getQuestionImage(),
                        question.getQuestionDetail(),
                        question.getQuestionTypeId()
                ))
                .toList();
    }

    @Override
    public CreateQuestionResponseDto createQuestion(CreateQuestionRequestDto createQuestionRequestDto) {
        SubTopicResponseDto subTopic = subTopicMapper.getSubTopicById(createQuestionRequestDto.getSubTopicId());

        QuestionType questionType = questionTypeMapper.getQuestionTypeById(createQuestionRequestDto.getQuestionTypeId());

        if (subTopic == null) {
            throw new SubTopicNotFoundException("SubTopic not found for id: " + createQuestionRequestDto.getSubTopicId());
        }
        if (questionType == null) {
            throw new QuestionTypeNotFoundException("QuestionType not found for id: " + createQuestionRequestDto.getQuestionTypeId());
        }
        Question question = new Question();
        question.setQuestionTitle(createQuestionRequestDto.getQuestionTitle());
        question.setQuestionImage(createQuestionRequestDto.getQuestionImage());
        question.setQuestionDetail(createQuestionRequestDto.getQuestionDetail());
        question.setQuestionTypeId(createQuestionRequestDto.getQuestionTypeId());
        question.setSubTopicId(createQuestionRequestDto.getSubTopicId());
        questionMapper.createQuestion(question);

        return new CreateQuestionResponseDto(
                question.getQuestionTitle(),
                question.getQuestionImage(),
                question.getQuestionDetail(),
                question.getQuestionTypeId(),
                question.getSubTopicId()
        );
    }

    @Override
    public UpdateQuestionResponseDto updateQuestion(UpdateQuestionRequestDto updateQuestionRequestDto) {
        Question existingQuestion = questionMapper.selectById(updateQuestionRequestDto.getId());
        if (existingQuestion == null) {
            throw new QuestionNotFoundException("Question not found for id: " + updateQuestionRequestDto.getId());
        }

        SubTopicResponseDto subTopic = subTopicMapper.getSubTopicById(updateQuestionRequestDto.getSubTopicId());
        if (subTopic == null) {
            throw new SubTopicNotFoundException("SubTopic not found for id: " + updateQuestionRequestDto.getSubTopicId());
        }

        QuestionType questionType = questionTypeMapper.getQuestionTypeById(updateQuestionRequestDto.getQuestionTypeId());
        if (questionType == null) {
            throw new QuestionTypeNotFoundException("QuestionType not found for id: " + updateQuestionRequestDto.getQuestionTypeId());
        }

        existingQuestion.setQuestionTitle(updateQuestionRequestDto.getQuestionTitle());
        existingQuestion.setQuestionImage(updateQuestionRequestDto.getQuestionImage());
        existingQuestion.setQuestionDetail(updateQuestionRequestDto.getQuestionDetail());
        existingQuestion.setQuestionTypeId(updateQuestionRequestDto.getQuestionTypeId());
        existingQuestion.setSubTopicId(updateQuestionRequestDto.getSubTopicId());

        Integer rowsAffected = questionMapper.updateQuestion(existingQuestion);
        if (rowsAffected == 0) {
            throw new UpdateFailedException("Failed to update question with id: " + updateQuestionRequestDto.getId());
        }

        return new UpdateQuestionResponseDto(
                existingQuestion.getId(),
                existingQuestion.getQuestionTitle(),
                existingQuestion.getQuestionImage(),
                existingQuestion.getQuestionDetail(),
                existingQuestion.getQuestionTypeId(),
                existingQuestion.getSubTopicId()
        );
    }

    @Override
    public void deleteQuestion(Integer questionId) {
        Question existingQuestion = questionMapper.selectById(questionId);
        if (existingQuestion == null) {
            throw new QuestionNotFoundException("Question not found for id: " + questionId);
        }

        Integer rowsAffected = questionMapper.deleteQuestion(questionId);
        if (rowsAffected == 0) {
            throw new UpdateFailedException("Failed to delete question with id: " + questionId);
        }
    }
}
