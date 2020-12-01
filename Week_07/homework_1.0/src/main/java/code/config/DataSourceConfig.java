package code.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源配置文件
 *
 * @author wangwenjie
 * @date 2020-12-01
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariConfig masterConfig(){
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public HikariConfig slaveConfig(){
        return new HikariConfig();
    }

    //主数据源
    @Bean
    public DataSource master(){
        return new HikariDataSource(masterConfig());
    }

    //从数据源
    @Bean
    public DataSource slave(HikariConfig slaveConfig){
        return new HikariDataSource(slaveConfig);
    }


}
