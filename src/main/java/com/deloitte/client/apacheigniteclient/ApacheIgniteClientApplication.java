package com.deloitte.client.apacheigniteclient;

import com.deloitte.client.apacheigniteclient.model.Employee;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApacheIgniteClientApplication{

    public static void main(String[] args) {
        SpringApplication.run(ApacheIgniteClientApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(Ignite ignite) {
        return args -> {
            IgniteCache<String, Employee> cache = ignite.getOrCreateCache("dummy");
            Employee employee = new Employee("123", "John", "Doe", 100.0);
            cache.put("key1",  employee);


            System.out.println(cache.get("key1"));

        };
    }

}
