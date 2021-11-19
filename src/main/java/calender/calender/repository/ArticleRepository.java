package calender.calender.repository;

import calender.calender.domain.Article;
import calender.calender.dto.ArticleResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select distinct new calender.calender.dto.ArticleResponse(user.loginId, article.title, "
        + "article.createdDate) from Article article "
        + "inner join article.user user "
        + "where article.year = :year and article.month = :month and article.day = :day")
    List<ArticleResponse> findAllByDate(
        @Param("year") int year,
        @Param("month") int month,
        @Param("day") int day);
}
