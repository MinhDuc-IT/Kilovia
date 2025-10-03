package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sub_topics")
public class SubTopic {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("sub_topic_title")
    private String subTopicTitle;

    @TableField("topic_id")
    private Integer topicId;
}
