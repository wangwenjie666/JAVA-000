package code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 *
 * @author wangwenjie
 * @date 2020-12-02
 */
@Configuration
public class RoutingDataSourceConfig {

    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceContextHolder.MASTER, null);
        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {

            @Override
            protected Object determineCurrentLookupKey() {
                return null;
            }
        };
        return null;
    }
}
