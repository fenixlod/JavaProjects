package com.project.books.additional;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.project.books.data.BooksDataStorageDB;
import com.project.books.data.BooksDataStorageInMemory;
import com.project.books.data.IBooksDataStorage;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages="com.project.books")
@EnableWebMvc
public class Configuration extends WebMvcConfigurerAdapter {
	private DataStrorageType DSType = DataStrorageType.DATABASE;
	
	@Bean
    public IBooksDataStorage getDataSourceInstance() {
		if(DSType == DataStrorageType.INMEMORY) {
			return new BooksDataStorageInMemory();
		}
		else {
			return new BooksDataStorageDB(getDataSource());
		}
    }
	
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/BooksDB");
        dataSource.setUsername("root");
        //dataSource.setPassword("P@ssw0rd");
        return dataSource;
    }
}

enum DataStrorageType {
	INMEMORY,DATABASE
}
