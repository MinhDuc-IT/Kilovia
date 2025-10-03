package com.example.kilovia_backend.dtos.responseDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicWithSubTopicResponseDto {
    private Integer id;
    private String topicTitle;
    private List<SubTopicResponseDto> subTopics;
}
