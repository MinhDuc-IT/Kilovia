package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "questions", autoResultMap = true)
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("question_title")
    private String questionTitle;

    @TableField("question_image")
    private String questionImage;

    @TableField(value = "question_detail", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> questionDetail; // JSON thành Map

    @TableField("question_type_id")
    private Integer questionTypeId;

    @TableField("sub_topic_id")
    private Integer subTopicId;
}
