package code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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

    private final DataSource master;
    private final DataSource slave;

    public RoutingDataSourceConfig(DataSource master, DataSource slave) {
        this.master = master;
        this.slave = slave;
    }


    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceContextHolder.MASTER, master);
        dataSourceMap.put(DataSourceContextHolder.SLAVE, slave);
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                String key = DataSourceContextHolder.getMasterOrSlave();
                if (key == null) {
                    return DataSourceContextHolder.MASTER;
                }
                return key;
            }
        };
        proxy.setDefaultTargetDataSource(master);
        proxy.setTargetDataSources(dataSourceMap);
        return proxy;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(routingDataSource());
    }
}
