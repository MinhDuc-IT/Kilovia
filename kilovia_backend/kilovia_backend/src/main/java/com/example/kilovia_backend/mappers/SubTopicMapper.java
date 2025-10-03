package com.example.kilovia_backend.mappers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kilovia_backend.dtos.requestDtos.CreateSubTopicRequestDto;
import com.example.kilovia_backend.dtos.responseDtos.CreateSubTopicResponseDto;
import com.example.kilovia_backend.dtos.responseDtos.SubTopicResponseDto;
import com.example.kilovia_backend.models.SubTopic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubTopicMapper extends BaseMapper<SubTopic> {
    @Select("SELECT * FROM sub_topics WHERE topic_id = #{topicId}")
    List<SubTopicResponseDto> getSubTopicsByTopicId(int topicId);

    @Select("SELECT * FROM sub_topics WHERE id = #{id}")
    SubTopicResponseDto getSubTopicById(int id);

    @Insert("INSERT INTO sub_topics(sub_topic_title, topic_id) VALUES(#{subTopicTitle}, #{topicId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createSubTopic(SubTopic subTopic);

    @Delete("DELETE FROM sub_topics WHERE id = #{id}")
    Integer deleteSubTopic(Integer id);

    @Update("UPDATE sub_topics SET sub_topic_title = #{subTopicTitle}, topic_id = #{topicId} WHERE id = #{id}")
    Integer updateSubTopic(SubTopic subTopic);
}
