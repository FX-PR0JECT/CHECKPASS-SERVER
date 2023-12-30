package FXPROJECT.CHECKPASS.common;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class ConnectionTest {


    @Autowired
    DataSource ds;

    @Test
    void test() throws SQLException {

        Connection connection = ds.getConnection();
        log.info("con : {}" , connection);
        Assertions.assertThat(connection).isNotNull();

    }


}
