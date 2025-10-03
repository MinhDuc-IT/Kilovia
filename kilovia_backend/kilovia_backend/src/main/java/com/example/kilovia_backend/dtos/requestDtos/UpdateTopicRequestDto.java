package com.example.kilovia_backend.dtos.requestDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTopicRequestDto {
    private Integer id;
    private String topicTitle;
    private Integer subjectGradeId;
}
