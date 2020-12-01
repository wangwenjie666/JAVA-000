package code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * jdbcTemplate config
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Configuration
public class JdbcTemplateConfig {

    private final DataSource master;
    private final DataSource slave;

    public JdbcTemplateConfig(DataSource master, DataSource slave) {
        this.master = master;
        this.slave = slave;
    }

    @Bean
    JdbcTemplate masterTemplate() {
        return new JdbcTemplate(master);
    }

    @Bean
    JdbcTemplate slaveTemplate() {
        return new JdbcTemplate(slave);
    }


}
