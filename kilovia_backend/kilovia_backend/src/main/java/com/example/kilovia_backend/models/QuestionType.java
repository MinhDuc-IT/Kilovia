package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("question_types")
public class QuestionType {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("type_name")
    private String typeName;
}
