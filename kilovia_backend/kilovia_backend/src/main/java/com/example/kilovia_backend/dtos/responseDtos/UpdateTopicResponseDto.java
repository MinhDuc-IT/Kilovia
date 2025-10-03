package com.example.kilovia_backend.dtos.responseDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTopicResponseDto {
    private Integer id;
    private String topicTitle;
    private Integer subjectGradeId;
}
