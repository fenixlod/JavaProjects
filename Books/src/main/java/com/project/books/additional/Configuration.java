package com.project.books.additional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.project.books.data.BooksDataStorageInMemory;
import com.project.books.data.IBooksDataStorage;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages="com.project.books")
@EnableWebMvc
public class Configuration extends WebMvcConfigurerAdapter {
	
	@Bean
    public IBooksDataStorage getDataSorce() {
        return new BooksDataStorageInMemory();
    }
}
