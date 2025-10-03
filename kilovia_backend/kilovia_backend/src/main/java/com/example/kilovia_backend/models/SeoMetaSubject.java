package com.example.kilovia_backend.models;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("seo_meta_subjects")
public class SeoMetaSubject {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("meta_title")
    private String metaTitle;

    @TableField("meta_description")
    private String metaDescription;

    @TableField("meta_keywords")
    private String metaKeywords;

    @TableField("meta_robots")
    private String metaRobots;

    @TableField("og_image")
    private String ogImage;

    @TableField("canonical_url")
    private String canonicalUrl;

    @TableField("subject_id")
    private Integer subjectId;
}
