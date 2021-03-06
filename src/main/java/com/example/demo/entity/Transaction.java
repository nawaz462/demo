
package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRANSACTION", schema = "nawaz")
public class Transaction implements Serializable{

    public Transaction(){
    }

    public Transaction(int transactionId, int transactionMessageId, String transactionStatus, String payload){

        this.transactionId = transactionId;
        this.transactionMessageId = transactionMessageId;
        this.transactionStatus = transactionStatus;
        this.payload = payload;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    @Column(name = "TRAN_MESSAGE_ID")
    private Integer transactionMessageId;

    @Column(name = "TRAN_STATUS")
    private String transactionStatus;

    @Column(name = "PAYLOAD")
    private String payload;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionMessageId() {
        return transactionMessageId;
    }

    public void setTransactionMessageId(int transactionMessageId) {
        this.transactionMessageId = transactionMessageId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString(){
        StringBuilder jsonData = new StringBuilder();
        jsonData.append("transactionId: ").append(transactionId);
        jsonData.append("\ntransactionMessageId: ").append(transactionMessageId);
        jsonData.append("\ntransactionStatus: ").append(transactionStatus);
        jsonData.append("\npayload: ").append(payload);
        return jsonData.toString();
    }
}
