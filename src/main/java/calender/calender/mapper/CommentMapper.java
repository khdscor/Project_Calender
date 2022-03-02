package calender.calender.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO  comment(content, article_id, user_id) VALUES (#{content}, #{article_id}, #{user_id})")
    void createComment(String content, Long article_id, Long user_id);
}
