package com.example.kilovia_backend.controllers;

import com.example.kilovia_backend.dtos.requestDtos.CreateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.requestDtos.UpdateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateSubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.UpdateSubTopicResponseDto;
import com.example.kilovia_backend.services.SubTopicService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subtopics")
public class SubTopicController {

    private final SubTopicService subTopicService;

    public SubTopicController(SubTopicService subTopicService) {
        this.subTopicService = subTopicService;
    }

    @GetMapping("/by-topic/{topicId}")
    public ResponseEntity<List<SubTopicResponseDto>> getSubTopicsByTopic(@PathVariable Integer topicId) {
        List<SubTopicResponseDto> subTopics = subTopicService.getSubTopicsByTopicId(topicId);
        return ResponseEntity.ok(subTopics);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateSubTopicResponseDto> createSubTopic(@RequestBody CreateSubTopicRequestDto createSubTopicRequestDto) {
        CreateSubTopicResponseDto createdSubTopic = subTopicService.createSubTopic(createSubTopicRequestDto);
        return ResponseEntity.ok(createdSubTopic);
    }

    @DeleteMapping("/delete/{subTopicId}")
    public ResponseEntity<Void> deleteSubTopic(@PathVariable Integer subTopicId) {
        subTopicService.deleteSubTopic(subTopicId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateSubTopicResponseDto> updateSubTopic(@RequestBody UpdateSubTopicRequestDto updateSubTopicRequestDto) {
        UpdateSubTopicResponseDto updatedSubTopic = subTopicService.updateSubTopic(updateSubTopicRequestDto);
        return ResponseEntity.ok(updatedSubTopic);
    }
}
