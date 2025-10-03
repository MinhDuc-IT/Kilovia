package com.example.kilovia_backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kilovia_backend.dtos.responseDtos.SubjectResponseDto;
import com.example.kilovia_backend.models.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
    @Select("SELECT * FROM subjects WHERE id = #{id}")
    Subject getSubjectById(int id);

    @Select("SELECT * FROM subjects")
    java.util.List<SubjectResponseDto> getAllSubjects();
}
