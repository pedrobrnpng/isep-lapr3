/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Utilizador
 * Created: 12/dez/2019
 */
    
CREATE OR REPLACE TRIGGER addInvoiceToClient AFTER INSERT ON Clients
    FOR EACH ROW
    DECLARE
        n int;
        month_num int;
        id_invoicep int;
    BEGIN
        month_num:=EXTRACT(MONTH FROM CURRENT_DATE);
        SELECT COUNT(*) INTO n FROM Invoice;
        IF (n!=0) THEN
            SELECT MAX(id_invoice) INTO id_invoicep FROM Invoice;
            INSERT INTO INVOICE VALUES(id_invoicep+1, :new.email, '0001-01-01', month_num, 0, 14.99);
        ELSE
            INSERT INTO INVOICE VALUES(1, :new.email, '0001-01-01', month_num, 0, 14.99);
        END IF;
    END;
    
/*CREATE OR REPLACE TRIGGER updateInvoiceAfterPayment AFTER UPDATE ON Invoice
    FOR EACH ROW
    DECLARE
        pragma autonomous_transaction;
        month_num int;
    BEGIN
        IF (:new.date_invoice NOT LIKE '0001-01-01') THEN
            month_num:=EXTRACT(MONTH FROM CURRENT_DATE);
            INSERT INTO Invoice(email, date_invoice, period, used_points, total_cost) VALUES(:new.email, '0001-01-01', month_num, 0, 14.99);
            COMMIT;
        END IF;
    END;*/

