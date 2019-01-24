package com.example.mail_project.service;


import com.example.mail_project.entity.CustomerLog;
import com.example.mail_project.entity.LineDataImg;
import com.example.mail_project.entity.MasterDataDetail;
import com.example.mail_project.repository.CustomerLogRepository;
import com.example.mail_project.repository.LineDataImgRepository;
import com.example.mail_project.repository.MasterDataDetailRepository;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppMailServiceImp implements AppMailDataService {

    @Autowired
    private CustomerLogRepository customerLogRepository;

    @Autowired
    private LineDataImgRepository lineDataImgRepository;

    @Autowired
    private MasterDataDetailRepository masterDataDetailRepository;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void SaveByJson(String json) {
        Gson gson = new Gson();
        CustomerLog customerLog = gson.fromJson(json, CustomerLog.class);
        customerLogRepository.save(customerLog);
        LOGGER.info("Save By Json : {}",customerLog.getId());
    }
    @Override
    public void SaveByJsonCus(String json) {
        Gson gson = new Gson();
        LOGGER.info("Json : {}",json);
        CustomerLog customerLog = gson.fromJson(json, CustomerLog.class);
        customerLogRepository.save(customerLog);
        LOGGER.info("Save By Json : {}",customerLog.getId());
    }

    @Override
    public void DeleteByID(Long id) {
        customerLogRepository.deleteById(id);
        LOGGER.info("Delete Success");
    }

    @Override
    public List<CustomerLog> getAllCusData() {
        return customerLogRepository.findAll();
    }

    @Override
    public CustomerLog findByID(Long ID) {
        return customerLogRepository.findById(ID).get();
    }

    @Override
    public List<CustomerLog> getNewData() {
        return null;
    }

    @Override
    public List<CustomerLog> getAllData(String sender) {
        return customerLogRepository.findByFirstNameContainingIgnoreCase(sender);
    }

    @Override
    public CustomerLog EditData(CustomerLog customerLog, Long id) {
        customerLog.setId(id);
        customerLogRepository.save(customerLog);
        LOGGER.info("Edit : {}", customerLog.getId());
        return customerLogRepository.findById(id).get();
    }

    @Override
    public List<CustomerLog> findBySender(String sender1) {
        return customerLogRepository.findByFirstNameContainingIgnoreCase(sender1);
    }

    @Override
    public List<CustomerLog> findBySubject(String subject) {
        return customerLogRepository.findBySubjectContainingIgnoreCase(subject);
    }

    @Override
    public List<CustomerLog> findByEmail(String email) {
        return customerLogRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<CustomerLog> findByResponsible(String responsible) {
        return customerLogRepository.findByResponsibleContainingIgnoreCase(responsible);
    }

    @Override
    public void SaveLineText(String json) {
        Gson gson = new Gson();
        CustomerLog customerLog = gson.fromJson(json, CustomerLog.class);
        customerLogRepository.save(customerLog);
    }

    @Override
    public void SaveLineImg(String json) {
        Gson gson = new Gson();
        LineDataImg lineDataImg = gson.fromJson(json, LineDataImg.class);
        lineDataImgRepository.save(lineDataImg);
    }

    @Override
    public List<CustomerLog> findByContent(String content) {
        return customerLogRepository.findByMsgContainingIgnoreCase(content);
    }


    @Override
    public List<CustomerLog> findByLevel(String level) {
        return customerLogRepository.findByLevelContaining(level);
    }

    @Override
    public List<CustomerLog> findByStatus(String status) {
        return customerLogRepository.findByStatusContaining(status);
    }

    @Override
    public List<CustomerLog> findBytype(String type) {
        return customerLogRepository.findBytypeContainingIgnoreCase(type);
    }

    @Override
    public List<LineDataImg> findByIdlineAndImgg(String idline) {
        return lineDataImgRepository.findByIdlineAndImgg(idline);
    }

    @Override
    public List<CustomerLog> findbyAll(String sender, String subject, String email, String responsible, String msg, String status, String level, String type) {
        return customerLogRepository.findBySenderContainingAndSubjectContainingAndEmailContainingAndResponsibleContainingAndMsgContainingAndLevelContainingAndStatusContainingAndTypeContaining(sender,subject,email,responsible,msg,status,level,type);
    }


    @Override
    public List<CustomerLog> findBySenderAndType(String sender, String type) {
        return customerLogRepository.findBySenderContainingAndTypeContaining(sender, type);
    }

    @Override
    public List<CustomerLog> findBySubjectAndType(String subject, String type) {
        return customerLogRepository.findBySenderContainingAndTypeContaining(subject, type);
    }

    @Override
    public List<CustomerLog> findByEmailAndType(String email, String type) {
        return customerLogRepository.findByEmailContainingAndTypeContaining(email, type);
    }

    @Override
    public List<CustomerLog> findByResponsibleAndType(String responsible, String type) {
        return customerLogRepository.findByResponsibleContainingAndTypeContaining(responsible, type);
    }

    @Override
    public List<CustomerLog> findByMsgAndType(String msg, String type) {
        return customerLogRepository.findByMsgContainingAndTypeContaining(msg,type);
    }

//    @Override
//    public List<MasterDataDetail> findByMasterDataDetai(String variable1) {
//        return masterDataDetailRepository.findByMasterDataDetai(variable1);
//    }

    @Override
    public List<MasterDataDetail> MasterDatakey(Long id) {
        return masterDataDetailRepository.findMasterDataDetailsByIdEquals(id);
    }
}
