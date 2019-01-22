package com.example.mail_project;

import com.example.mail_project.entity.CustomerLog;
import com.example.mail_project.repository.CustomerLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLog implements CommandLineRunner{

    @Autowired
    private CustomerLogRepository customerLogRepository;

    @Override
    public void run(String... args) throws Exception {

        CustomerLog customerLog = new CustomerLog();

        customerLog.setSender("chitsanu");
        customerLog.setSend_To("boonkhun");
        customerLog.setEmail("pang_lovegood@hotmail.com");
        customerLog.setMsg("pang to test");
        customerLog.setSentDate("15/12/18");
        customerLog.setStatus("test");
        customerLog.setType("pang");

        customerLogRepository.save(customerLog);

    }
}
