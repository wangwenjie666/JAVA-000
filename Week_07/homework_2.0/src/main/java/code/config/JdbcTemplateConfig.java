package code.config;

import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * jdbc config
 *
 * @author wangwenjie
 * @date 2020-12-03
 */
@Configuration
public class JdbcTemplateConfig {


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }
}
