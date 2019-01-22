package com.example.mail_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineDataImg {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String imgg;
    private String idline;
    private String sentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerLog")
    private CustomerLog customerLog;
}
