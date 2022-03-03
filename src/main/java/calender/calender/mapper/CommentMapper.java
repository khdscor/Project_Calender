package calender.calender.mapper;

import java.util.Date;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO  comment(content, created_date, article_id, user_id) VALUES (#{content}, #{created_date}, #{article_id}, #{user_id})")
    void createComment(String content, Long article_id, Long user_id, Date created_date);
}
