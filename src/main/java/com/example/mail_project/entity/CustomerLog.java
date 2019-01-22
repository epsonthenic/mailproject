package com.example.mail_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//ตาราง
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
public class CustomerLog {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String sender;//sender
    private String send_To;//sender_To
    private String subject;//subject
    private String email;//email

    @Length(max = 4000)
    private String msg;//msg

    private String attachments;
    private String responsible;
    private String sentDate;//send_date
    private String status;//status
    private String type;//type
    private String level="0";//level
    private String CC;
    private String BCC;
    private String idline;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerLog")
    private Set<LineDataImg> details = new HashSet<LineDataImg>();

}