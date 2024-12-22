package com.example.demo;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSourceInitializer implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            // 可以执行一个简单的查询来验证连接
            connection.createStatement().execute("SELECT 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
