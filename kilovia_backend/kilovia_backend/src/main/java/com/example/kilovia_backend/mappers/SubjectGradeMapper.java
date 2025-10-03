package com.example.kilovia_backend.mappers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kilovia_backend.models.SubjectGrade;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SubjectGradeMapper extends BaseMapper<SubjectGrade> {
    @Select("SELECT * FROM subject_grades WHERE grade_id = #{gradeId} AND subject_id = #{subjectId}")
    SubjectGrade getSubjectGradesByGradeIdAndSubjectId(int gradeId, int subjectId);

    @Select("SELECT * FROM subject_grades WHERE grade_id = #{gradeId}")
    java.util.List<SubjectGrade> getAllSubjectGradesByGradeId(int gradeId);

    @Select("SELECT * FROM subject_grades WHERE subject_id = #{subjectId}")
    java.util.List<SubjectGrade> getAllSubjectGradesBySubjectId(int subjectId);

    @Insert("INSERT INTO subject_grades(subject_grade_name, subject_id, grade_id) VALUES(#{subjectGradeName}, #{subjectId}, #{gradeId})")
    void createSubjectGrade(SubjectGrade subjectGrade);

    @Update("UPDATE subject_grades SET subject_grade_name = #{subjectGradeName}, subject_id = #{subjectId}, grade_id = #{gradeId} WHERE id = #{id}")
    Integer updateSubjectGrade(SubjectGrade subjectGrade);

    @Delete("DELETE FROM subject_grades WHERE id = #{id}")
    Integer deleteSubjectGrade(Integer id);
}
