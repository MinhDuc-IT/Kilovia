package com.example.kilovia_backend.mappers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.example.kilovia_backend.models.Question;
import com.example.kilovia_backend.models.SubjectGrade;
import org.apache.ibatis.annotations.*;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    @Select("SELECT * FROM questions WHERE sub_topic_id = #{subTopicId}")
    @Results(id = "QuestionMap", value = {
        @Result(column = "id", property = "id"),
        @Result(column = "question_title", property = "questionTitle"),
        @Result(column = "question_image", property = "questionImage"),
        @Result(column = "question_detail", property = "questionDetail", typeHandler = JacksonTypeHandler.class),
        @Result(column = "question_type_id", property = "questionTypeId"),
        @Result(column = "sub_topic_id", property = "subTopicId")
    })
    java.util.List<Question> getQuestionsBySubTopicId(int subTopicId);

    @Insert("INSERT INTO questions(question_title, question_image, question_detail, question_type_id, sub_topic_id) " +
            "VALUES(#{questionTitle}, #{questionImage}, #{questionDetail, typeHandler=com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler}, " +
            "#{questionTypeId}, #{subTopicId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createQuestion(Question question);

    @Update("UPDATE questions SET question_title = #{questionTitle}, question_image = #{questionImage}, " +
            "question_detail = #{questionDetail, typeHandler=com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler}, " +
            "question_type_id = #{questionTypeId}, sub_topic_id = #{subTopicId} WHERE id = #{id}")
    Integer updateQuestion(Question question);

    @Delete("DELETE FROM questions WHERE id = #{id}")
    Integer deleteQuestion(int questionId);
}
