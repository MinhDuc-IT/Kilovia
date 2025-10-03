package com.example.kilovia_backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kilovia_backend.dtos.responseDtos.GradeResponseDto;
import com.example.kilovia_backend.models.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GradeMapper extends BaseMapper<Grade> {
    @Select("SELECT * FROM grades")
    List<GradeResponseDto> getAllGrades();

    @Insert("INSERT INTO grades(grade_name) VALUES(#{gradeName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createGrade(Grade grade);

    @Update("UPDATE grades SET grade_name = #{gradeName} WHERE id = #{id}")
    Integer updateGrade(Grade grade);

    @Delete("DELETE FROM grades WHERE id = #{id}")
    Integer deleteGrade(Integer id);
}
