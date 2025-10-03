package com.example.kilovia_backend.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kilovia_backend.dtos.responseDtos.TopicResponseDto;
import com.example.kilovia_backend.models.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {
    @Select("SELECT * FROM topics WHERE subject_grade_id = #{subjectGradeId}")
    List<TopicResponseDto> getTopicsBySubjectGradeId(int subjectGradeId);

    @Insert("INSERT INTO topics(topic_title, subject_grade_id) VALUES(#{topicTitle}, #{subjectGradeId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createTopic(Topic topic);

    @Update("UPDATE topics SET topic_title = #{topicTitle}, subject_grade_id = #{subjectGradeId} WHERE id = #{id}")
    Integer updateTopic(Topic topic);

    @Delete("DELETE FROM topics WHERE id = #{id}")
    Integer deleteTopic(Integer id);
}
