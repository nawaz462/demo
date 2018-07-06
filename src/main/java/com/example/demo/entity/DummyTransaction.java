
package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DUMMY_TRANSACTION", schema = "nawaz")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "DUMMY_PROC",
                procedureName = "DUMMY_PROC",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "TRANSACT_ID", type = Integer.class)
                })
})
public class DummyTransaction implements Serializable{

    public DummyTransaction(){
    }

    public DummyTransaction(int transactionId, int transactionMessageId, String transactionStatus, String payload){

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
