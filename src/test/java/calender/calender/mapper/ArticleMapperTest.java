package calender.calender.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void findById(){
        System.out.println(articleMapper.findById(111L));
        System.out.println(articleMapper.findById(1L));

    }
}
