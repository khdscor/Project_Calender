package calender.calender;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class CalenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalenderApplication.class, args);
    }
}
