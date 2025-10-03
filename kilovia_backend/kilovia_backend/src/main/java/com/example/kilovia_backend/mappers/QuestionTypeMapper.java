package com.example.kilovia_backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kilovia_backend.models.QuestionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionTypeMapper extends BaseMapper<QuestionType> {
    @Select("SELECT * FROM question_types WHERE id = #{id}")
    QuestionType getQuestionTypeById(int id);
}
