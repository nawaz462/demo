package com.example.demo.repopsitory;

import com.example.demo.entity.DummyTransaction;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyTransactionRepo extends JpaRepository<DummyTransaction,Integer>{

    @Procedure(name = "DUMMY_PROC")
    void markStatusProcedureCall(@Param("TRANSACT_ID") Integer trasactionId);

}
