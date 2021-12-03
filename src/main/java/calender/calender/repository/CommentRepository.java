package calender.calender.repository;

import calender.calender.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select distinct comment from Comment comment "
        + "join fetch comment.user user "
        + "where comment.article.id = :articleId "
        + "order by comment.createdDate desc")
    List<Comment> findAllByArticleId(@Param("articleId") Long articleId);
}
