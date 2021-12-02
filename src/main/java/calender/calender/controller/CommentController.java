package calender.calender.controller;

import calender.calender.dto.CommentRequest;
import calender.calender.exception.NotExistsUserException;
import calender.calender.security.PrincipalDetails;
import calender.calender.service.CommentService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String write(CommentRequest commentRequest,
        @AuthenticationPrincipal PrincipalDetails user) {
        if (Objects.isNull(user)) {
            throw new NotExistsUserException("로그인 하세요!");
        }
        commentService.write(commentRequest.getArticleId(), user.getUserId(),
            commentRequest.getContent());

        return "redirect:/articles/" + commentRequest.getArticleId();
    }
}
