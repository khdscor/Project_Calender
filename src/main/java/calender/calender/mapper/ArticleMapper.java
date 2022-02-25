package calender.calender.mapper;

import calender.calender.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper {

    @Select("SELECT * FROM article WHERE id = #{id}")
    Article findById(Long id);
}
