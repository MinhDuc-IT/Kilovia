package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("subjects")
public class Subject {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("subject_name")
    private String subjectName;
}
