package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("subject_grades")
public class SubjectGrade {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("subject_grade_name")
    private String subjectGradeName;

    @TableField("subject_id")
    private Integer subjectId;

    @TableField("grade_id")
    private Integer gradeId;
}
