package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("topics")
public class Topic {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("topic_title")
    private String topicTitle;

    @TableField("subject_grade_id")
    private Integer subjectGradeId;
}
