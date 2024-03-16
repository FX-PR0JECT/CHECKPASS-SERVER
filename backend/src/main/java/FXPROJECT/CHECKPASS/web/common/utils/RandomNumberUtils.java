package FXPROJECT.CHECKPASS.web.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RandomNumberUtils {

    public void getToken(){

        int random = (int) (Math.random() * 10000);

        log.info("random : {}" , random);
    }

}
