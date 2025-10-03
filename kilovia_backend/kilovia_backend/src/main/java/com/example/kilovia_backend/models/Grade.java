package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("grades")
public class Grade {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("grade_name")
    private String gradeName;
}
