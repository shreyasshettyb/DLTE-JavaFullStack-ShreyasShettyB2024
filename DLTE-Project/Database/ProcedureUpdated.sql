create or replace PROCEDURE avail_deposits (
    p_customer_id      NUMBER,
    p_deposit_id       NUMBER,
    p_deposit_amount   NUMBER,
    p_deposit_duration INT,
    p_deposit_maturity DATE,
    p_result           OUT VARCHAR
) AS
    v_ack             NUMBER;
    v_customer_status VARCHAR(25);
    v_deposit_status  NUMBER;
BEGIN
    -- Check customer status
    SELECT CUSTOMER_STATUS INTO v_customer_status FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = p_customer_id;

    -- Check deposit status
    SELECT DEPOSIT_ID INTO v_deposit_status FROM mybank_app_depositsavailable WHERE DEPOSIT_ID = p_deposit_id;

    IF v_deposit_status IS NULL THEN
        p_result := 'Deposit not found';
    ELSIF v_customer_status = 'active' THEN
        -- Insert data into MYBANK_APP_DEPOSITSAVAILED table
        INSERT INTO MYBANK_APP_DEPOSITSAVAILED (
            deposit_avail_id,
            customer_id,
            deposit_id,
            deposited_amount,
            deposited_duration,
            deposit_maturity
        ) VALUES (
            MY_BANK_APP_SEQ_DEPOSITSGIVEN.nextval,
            p_customer_id,
            p_deposit_id,
            p_deposit_amount,
            p_deposit_duration,
            p_deposit_maturity
        );

        -- Check if the insertion was successful
        v_ack := SQL%ROWCOUNT;

        IF v_ack > 0 THEN
            p_result := 'Success';
        ELSE
            p_result := 'Fail';
            RAISE_APPLICATION_ERROR(-20001, 'Failed to insert data into MYBANK_APP_DEPOSITSAVAILED table');
        END IF;
    ELSE
        -- Customer is inactive, raise an application error
        p_result := 'Fail';
        RAISE_APPLICATION_ERROR(-20002, 'Customer is inactive');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
                p_result := 'Fail';
                RAISE_APPLICATION_ERROR(-20003, 'Deposit not found');
    WHEN OTHERS THEN
        -- Handle exceptions
        p_result := 'Fail';
        RAISE_APPLICATION_ERROR(-20004, SQLERRM);
END avail_deposits;