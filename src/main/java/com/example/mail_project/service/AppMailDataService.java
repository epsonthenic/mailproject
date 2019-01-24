package com.example.mail_project.service;

import com.example.mail_project.entity.CustomerLog;
import com.example.mail_project.entity.LineDataImg;
import com.example.mail_project.entity.MasterDataDetail;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppMailDataService {

    void SaveByJson(String json);

    void SaveByJsonCus(String json);

    void DeleteByID(Long id);

    List<CustomerLog> getAllCusData();

    List<CustomerLog> getAllData(String sender);

    //------------- find ---------------------//

    List<CustomerLog> findBySender(String sender1);

    List<CustomerLog> findBySubject(String subject);

    List<CustomerLog> findByEmail(String email);

    List<CustomerLog> findByResponsible(String responsible);

    List<CustomerLog> findBytype(String type);

    List<LineDataImg> findByIdlineAndImgg(String idline);

    List<CustomerLog> findByContent(String content);

    List<CustomerLog> findByLevel(String level);

    List<CustomerLog> findByStatus(String status);

    List<CustomerLog> findbyAll(String sender,String subject,String email,String responsible,String msg,String status,String level,String type);

    List<CustomerLog> findBySenderAndType(String sender,String type);

    List<CustomerLog> findBySubjectAndType(String subject,String type);

    List<CustomerLog> findByEmailAndType(String email,String type);

    List<CustomerLog> findByResponsibleAndType(String responsible,String type);

    List<CustomerLog> findByMsgAndType(String msg,String type);
    //----------------------------------------//

    List<CustomerLog> getNewData();

    CustomerLog findByID(Long ID);

    CustomerLog EditData(CustomerLog customerLog , Long id);


    void SaveLineText(String json);

    void SaveLineImg(String json);

//    List<MasterDataDetail> findByMasterDataDetai(String variable1);
    List<MasterDataDetail> MasterDatakey(Long id,String code);

    //ResponseEntity<String> seveLineTextToEmail(String jsonn);

    //ResponseEntity<String> seveLineImgToEmail(String jsonn);
}
