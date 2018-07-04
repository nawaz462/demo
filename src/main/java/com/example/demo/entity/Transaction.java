package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Clob;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "TRAN_MESSAGE_ID")
    private Long transactionMessageId;

    @Column(name = "TRAN_STATUS")
    private String transactionStatus;

    @Column(name = "PAYLOAD")
    private Clob payload;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionMessageId() {
        return transactionMessageId;
    }

    public void setTransactionMessageId(Long transactionMessageId) {
        this.transactionMessageId = transactionMessageId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Clob getPayload() {
        return payload;
    }

    public void setPayload(Clob payload) {
        this.payload = payload;
    }

    @Override
    public String toString(){
        String jsonData = "{ transactionId : "+ transactionId + ", transactionStatus : "  + transactionStatus + " }";
        return jsonData;
    }
}
