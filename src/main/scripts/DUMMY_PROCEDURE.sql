CREATE PROCEDURE `DUMMY_PROC`(IN `TRANSACT_ID` INT(255))
    NO SQL
Update nawaz.transaction SET transaction.TRAN_STATUS = 'COMPLETE' WHERE
transaction.TRANSACTION_ID = TRANSACT_ID