package com.example.kilovia_backend.dtos.responseDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionResponseDto {
    private Integer id;
    private String questionTitle;
    private String questionImage;
    private Map<String, Object> questionDetail;
    private Integer questionTypeId;
    private Integer subTopicId;
}
