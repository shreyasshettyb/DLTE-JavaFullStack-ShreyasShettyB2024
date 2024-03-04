--INSERT NEW TRANSACTION

CREATE OR REPLACE PROCEDURE INSERT_RECORDS(
    DATE_OF_TRANSACTION IN DATE,
    AMOUNT_IN_TRANSACTION IN NUMBER,
    TRANSACTION_TO IN VARCHAR2,
    TRANSACTION_REMARKS IN VARCHAR2,
)
AS
BEGIN
    INSERT INTO TRANSACTIONS VALUES(DATE_OF_TRANSACTION,AMOUNT_IN_TRANSACTION,TRANSACTION_TO,TRANSACTION_REMARKS);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('ERROR OCCURRED DURING TRANSACTION INSERTION: ' || SQLERRM);
    RAISE;
END INSERT_RECORDS;
/



--DELETE TRANSACTION

CREATE OR REPLACE PROCEDURE DELETE_RECORDS(
    TRANSACTION_TO IN VARCHAR2
)
AS
BEGIN
    DELETE FROM TRANSACTIONS WHERE SENTTO = TRANSACTION_TO;
    COMMIT;
EXCEPTION
     WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('SENT TO OT FOUND: '||TRANSACTION_TO);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR DUE TO: '||SQLERRM);
        RAISE;
END DELETE_RECORDS;
/


--FILTER TRANSACTION THOSE DONE FOR EDUCATION

CREATE OR REPLACE PROCEDURE FILTER_RECORDS(
    TRANSACTION_REMARKS IN VARCHAR2
)
AS
BEGIN
    FOR TRANS IN (SELECT * FROM TRANSACTIONS WHERE REMARKS = TRANSACTION_REMARKS) LOOP
    DBMS_OUTPUT.PUT_LINE('DATE: ' || TO_CHAR(TRANS.DATEOFTRANSACTION, 'DD-MON-YYYY') || 
                             ', AMOUNT: ' || TRANS.AMOUNTINTRANSACTION || 
                             ', TO: ' || TRANS.SENTTO);
    END LOOP;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('NO TRANSACTIONS FOUND FOR THE SPECIFIED REMARKS: ' || TRANSACTION_REMARKS);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('ERROR OCCURRED DURING TRANSACTION FILTERING: ' || SQLERRM);
        RAISE;
END FILTER_RECORDS;
/


--INPUTS
BEGIN
--INSERT_RECORDS('26FEB2024', 463, 'VARUN', 'EDUCATION');
--DELETE_RECORDS('RAKESH');
FILTER_RECORDS('EDUCATION');
END; 