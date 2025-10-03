package com.example.kilovia_backend.controllers;

import com.example.kilovia_backend.dtos.requestDtos.CreateTopicRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.TopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateTopicResponseDto;
import com.example.kilovia_backend.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/by-subject-grade/{subjectGradeId}")
    public ResponseEntity<List<TopicResponseDto>> getTopicsBySubjectGrade(@PathVariable Integer subjectGradeId) {
        List<TopicResponseDto> topics = topicService.getTopicsBySubjectGradeId(subjectGradeId);
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateTopicResponseDto> createTopic(@RequestBody CreateTopicRequestDto createTopicRequestDto) {
        CreateTopicResponseDto createdTopic = topicService.createTopic(createTopicRequestDto);
        return ResponseEntity.ok(createdTopic);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateTopicResponseDto> updateTopic(@RequestBody UpdateTopicRequestDto updateTopicRequestDto) {
        UpdateTopicResponseDto updatedTopic = topicService.updateTopic(updateTopicRequestDto);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/delete/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Integer topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }
}
