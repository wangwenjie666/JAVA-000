package code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * jdbcTemplate config
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Configuration
public class JdbcTemplateConfig {

    private final AbstractRoutingDataSource routingDataSource;

    public JdbcTemplateConfig(AbstractRoutingDataSource routingDataSource) {
        this.routingDataSource = routingDataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(routingDataSource);
    }

}
