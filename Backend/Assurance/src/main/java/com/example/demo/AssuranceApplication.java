package com.example.demo;


import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

@EnableTransactionManagement

@EnableAutoConfiguration(exclude = { //
        DataSourceAutoConfiguration.class, //
        DataSourceTransactionManagerAutoConfiguration.class, //
        HibernateJpaAutoConfiguration.class })
public class AssuranceApplication {
	
	 @Autowired
	    private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(AssuranceApplication.class, args);
	}
	
	 @Bean(name = "dataSource")
	    public DataSource getDataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 
	        // See: application.properties
	        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
	        dataSource.setUrl(env.getProperty("spring.datasource.url"));
	        dataSource.setUsername(env.getProperty("spring.datasource.username"));
	        dataSource.setPassword(env.getProperty("spring.datasource.password"));
	 
	        System.out.println("## getDataSource: " + dataSource);
	 
	        return dataSource;
	    }
	 
	 
	 @Autowired
	    @Bean(name = "sessionFactory")
	    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
	        Properties properties = new Properties();
	        System.out.println("## getSessefzoihoiahqfizhadeAIh");
	        // See: application.properties
	        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
	        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
	        properties.put("current_session_context_class", //
	                env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
	         
	         
	        // Fix Postgres JPA Error:
	        // Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
	        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);
	 
	        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	 
	        // Package contain entity classes
	        factoryBean.setPackagesToScan(new String[] { "com.example.demo.entities"});
	        factoryBean.setDataSource(dataSource);
	        factoryBean.setHibernateProperties(properties);
	        factoryBean.afterPropertiesSet();
	        System.out.println("## 111111111 getSessionFactory:"+factoryBean.getHibernateProperties());
	        //
	        SessionFactory sf = factoryBean.getObject();
	        if(sf==null) System.out.println("session is null");
	        System.out.println("## 22222222222 getSessionFactory: " + sf);
	        return sf;
	    }
	 
	    @Autowired
	    @Bean(name = "transactionManager")
	    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) throws Exception {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager(getSessionFactory(getDataSource()));
	 
	        return transactionManager;

}
	   /* @Autowired
		@Bean
		public HibernateTransactionManager hibernateTransactionManager() throws Exception{
		     return new HibernateTransactionManager(getSessionFactory(getDataSource()));
		}*/

}