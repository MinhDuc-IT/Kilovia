package com.example.kilovia_backend.dtos.requestDtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionRequestDto {
    private Integer id;
    private String questionTitle;
    private String questionImage;
    private Map<String, Object> questionDetail;
    private Integer questionTypeId;
    private Integer subTopicId;
}
