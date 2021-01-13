/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Utilizador
 * Created: 10/dez/2019
 */
 
-- ##Clients table
create or replace FUNCTION getClients RETURN SYS_REFCURSOR AS
    curClients SYS_REFCURSOR;
BEGIN
    OPEN curClients FOR SELECT * FROM Clients;
    RETURN curClients;
END;

create or replace PROCEDURE addClient(emailp Clients.email%type, namep Clients.nameclient%type, passp Clients.pass%type, agep Clients.age%type, heightp Clients.height%type, weightp Clients.weight%type, genderp Clients.gender%type) IS
BEGIN
    INSERT INTO Clients VALUES(emailp, namep, passp, agep, heightp, weightp, genderp, 0, 0);
END;

create or replace PROCEDURE updatePoints(emailp Clients.email%type, pointsp Clients.points%type) AS
BEGIN
    UPDATE Clients
    SET points= pointsp
    WHERE email LIKE emailp;
END;

create or replace FUNCTION getClient(emailp Clients.email%type) RETURN SYS_REFCURSOR IS
cli SYS_REFCURSOR;
BEGIN
    OPEN cli FOR SELECT * FROM Clients WHERE EMAIL=emailp;
    RETURN cli;
END;

create or replace PROCEDURE updateClient(emailp Clients.email%type, namep Clients.nameclient%type, agep Clients.age%type, heightp Clients.height%type, weightp Clients.weight%type, genderp Clients.gender%type, avgcyclingspeedp Clients.avgcyclingspeed%type, pointsp Clients.points%type) AS
BEGIN
    UPDATE Clients
    SET nameclient= namep,
        age= agep,
        height= heightp,
        weight= weightp,
        gender= genderp,
        avgcyclingspeed=avgcyclingspeedp,
        points=pointsp
    WHERE email=emailp;
END;

-- ##CreditCards table
create or replace FUNCTION getCreditCards RETURN SYS_REFCURSOR AS
    curCreditCards SYS_REFCURSOR;
BEGIN
    OPEN curCreditCards FOR SELECT * FROM Credit_Cards;
    RETURN curCreditCards;
END;

create or replace PROCEDURE addCreditCard(emailp Credit_Cards.email%type, tinp Credit_Cards.tin%type, expdatep Credit_Cards.expdate%type, creditsp Credit_Cards.credits%type) IS
BEGIN
    INSERT INTO Credit_Cards VALUES(tinp, emailp, expdatep, creditsp);
END;

create or replace FUNCTION getCreditCard(p_tin Credit_Cards.tin%type) RETURN SYS_REFCURSOR IS
cc SYS_REFCURSOR;
BEGIN
    OPEN cc FOR SELECT * FROM Credit_Cards WHERE TIN= p_tin;
    RETURN cc;
END;

create or replace FUNCTION getCreditCardEmail(emailp Credit_Cards.email%type) RETURN SYS_REFCURSOR IS
cc SYS_REFCURSOR;
BEGIN
    OPEN cc FOR SELECT * FROM Credit_Cards WHERE email= emailp;
    RETURN cc;
END;
    
create or replace PROCEDURE updateCreditCard(tin1 Credit_Cards.tin%type, expDate1 Credit_Cards.expdate%type, credits1 Credit_Cards.credits%type) AS
BEGIN
    UPDATE Credit_Cards
    SET expDate= expDate1,
        credits= credits1
    WHERE tin=tin1;
END;

-- ##Invoice table
create or replace FUNCTION getInvoices RETURN SYS_REFCURSOR AS
    curInvoices SYS_REFCURSOR;
BEGIN
    OPEN curInvoices FOR SELECT * FROM Invoice;
    RETURN curInvoices;
END;

create or replace FUNCTION getInvoice(invIdp Invoice.id_invoice%type) RETURN SYS_REFCURSOR AS
    curInvoices SYS_REFCURSOR;
BEGIN
    OPEN curInvoices FOR SELECT * FROM Invoice WHERE id_invoice=invIdp;
    RETURN curInvoices;
END;

create or replace PROCEDURE updateInvoice(id_invoicep Invoice.id_invoice%type, date_invoicep Invoice.date_invoice%type, periodp Invoice.period%type, used_pointsp Invoice.used_points%type, total_costp Invoice.total_cost%type) AS
BEGIN
    UPDATE Invoice
    SET date_invoice= date_invoicep,
        period= periodp,
        used_points=used_pointsp,
        total_cost=total_cost
    WHERE id_invoice=id_invoicep;
END;

create or replace PROCEDURE addInvoice(id_invoicep Invoice.id_invoice%type, date_invoicep Invoice.date_invoice%type, periodp Invoice.period%type, used_pointsp Invoice.used_points%type, total_costp Invoice.total_cost%type, emailp Invoice.email%type) IS
    BEGIN
        INSERT INTO Invoice VALUES(id_invoicep, emailp, date_invoicep, periodp, used_pointsp, total_costp);
    END;

create or replace FUNCTION getInvoiceEmail(emailp Invoice.email%type) RETURN SYS_REFCURSOR AS
    curInvoices SYS_REFCURSOR;
BEGIN
    OPEN curInvoices FOR SELECT * FROM (SELECT * FROM Invoice WHERE email LIKE emailp ORDER BY 1 DESC) WHERE ROWNUM <= 1;
    RETURN curInvoices;
END;

create or replace FUNCTION getAllInvoicesEmail(emailp Invoice.email%type) RETURN SYS_REFCURSOR AS
    curInvoices SYS_REFCURSOR;
BEGIN
    OPEN curInvoices FOR SELECT * FROM Invoice WHERE email LIKE emailp AND date_invoice NOT LIKE '0001-01-01';
    RETURN curInvoices;
END;

create or replace FUNCTION getInvoiceEmail(emailp Invoice.email%type) RETURN SYS_REFCURSOR AS
    curInvoices SYS_REFCURSOR;
BEGIN
    OPEN curInvoices FOR SELECT * FROM (SELECT * FROM Invoice WHERE email LIKE emailp ORDER BY 1 DESC) WHERE ROWNUM <= 1;
    RETURN curInvoices;
END;

create or replace FUNCTION getInvoiceMonthEmail(month Invoice.period%type, emailp Invoice.email%type) RETURN SYS_REFCURSOR AS
    curInvoices SYS_REFCURSOR;
BEGIN
    OPEN curInvoices FOR SELECT * FROM (SELECT * FROM Invoice WHERE email LIKE emailp AND month=period ORDER BY 1 DESC) WHERE ROWNUM <= 1;
    RETURN curInvoices;
END;

-- ##InvoiceLine table
create or replace FUNCTION getInvoiceLine(invIdp Invoice_Line.id_invoice%type, vehicle_request_idp Invoice_Line.vehicle_request_id%type) RETURN SYS_REFCURSOR AS
    curInvoiceLines SYS_REFCURSOR;
BEGIN
    OPEN curInvoiceLines FOR SELECT * FROM Invoice_Line WHERE id_invoice=invIdp AND vehicle_request_id=vehicle_request_idp;
    RETURN curInvoiceLines;
END;

create or replace FUNCTION getInvoiceLines(invIdp Invoice_Line.id_invoice%type) RETURN SYS_REFCURSOR AS
    curInvoiceLines SYS_REFCURSOR;
BEGIN
    OPEN curInvoiceLines FOR SELECT * FROM Invoice_Line WHERE id_invoice=invIdp;
    RETURN curInvoiceLines;
END;

create or replace PROCEDURE addInvoiceLine(invIdp Invoice_Line.id_invoice%type, vehicle_request_idp Invoice_Line.vehicle_request_id%type, costp Invoice_Line.cost%type) IS
BEGIN
    INSERT INTO Invoice_Line VALUES(invIdp, vehicle_request_idp, costp);
END;

/*Park DB*/ 
CREATE OR REPLACE FUNCTION getParks RETURN SYS_REFCURSOR AS
    curParks SYS_REFCURSOR;
    BEGIN
        OPEN curParks FOR SELECT * FROM PARKS P,Places Pl WHERE Active=1 AND P.id_park=Pl.id_place;
        RETURN curParks;
    END;

CREATE OR REPLACE PROCEDURE addPark(identification VARCHAR,p_latitude Places.latitude%type ,p_longitude Places.longitude%type,elevation Places.elevation%type, des VARCHAR,maxNumberBicycles int,maxNumberScooter int,inputVoltage NUMERIC,inputCurrent NUMERIC) IS
    p_id_place Places.id_place%type;
    n int;
    ex_same_location Exception;
    BEGIN
        SELECT COUNT(Pl.id_place) INTO n FROM Places Pl, Parks P WHERE Pl.latitude = p_latitude AND Pl.longitude=p_longitude AND Pl.id_place=P.id_park AND P.active=1;
        IF(n>0) THEN
            raise ex_same_location;
        END IF;
        INSERT INTO Places(latitude,longitude,elevation) VALUES (p_latitude,p_longitude,elevation);
        SELECT MAX(id_place) into p_id_place FROM Places;
        INSERT INTO PARKS VALUES(p_id_place,identification,des, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent,1);
    EXCEPTION
    WHEN ex_same_location THEN
            raise_application_error(-20001, 'Place already inserted in the database');
    END;

CREATE OR REPLACE FUNCTION getPark(id_p int) RETURN SYS_REFCURSOR IS
    park SYS_REFCURSOR;
    BEGIN
        OPEN park FOR SELECT * FROM PARKS P,Places Pl WHERE Active=1 AND id_park= id_p AND P.id_park=Pl.id_place;
        RETURN park;
    END;
    
CREATE OR REPLACE FUNCTION getParkByIdentification(identification_p VARCHAR) RETURN SYS_REFCURSOR IS
    park SYS_REFCURSOR;
    BEGIN
        OPEN park FOR SELECT * FROM PARKS P,Places Pl WHERE Active=1 AND P.identification= identification_p AND P.id_park=Pl.id_place;
        RETURN park;
    END;

CREATE OR REPLACE PROCEDURE updatePark(idPark int,p_identification varchar,p_latitude NUMERIC ,p_longitude NUMERIC,p_elevation NUMERIC, p_des VARCHAR,p_maxNumberBicycles int,p_maxNumberScooter int,p_inputVoltage NUMERIC,p_inputCurrent NUMERIC) AS
    BEGIN
        UPDATE Places
        Set latitude=p_latitude,
            longitude=p_longitude,
            elevation=p_elevation
        WHERE id_place=idPark;
        UPDATE PARKS
        SET identification= p_identification,
            description=p_des,
            maxNumberBicycles= p_maxNumberBicycles,
            maxNumberScooter =p_maxNumberScooter,
            inputVoltage=p_inputVoltage,
            inputCurrent=p_inputCurrent
        WHERE id_park=idPark;
    END;
    
CREATE OR REPLACE PROCEDURE deactivatePark(idPark int) AS
    BEGIN
       UPDATE PARKS
       SET ACTIVE=0
       WHERE id_park=idPark;
       
       UPDATE VEHICLES
       SET AVAILABLE=0
       WHERE ID_PARK=idPark;
    END;
    
/* POI DB */
CREATE OR REPLACE PROCEDURE addPOI(poi_latitude NUMERIC, poi_longitude NUMERIC, poi_elevation NUMERIC, poi_desc VARCHAR) AS
    poi_id_place Places.id_place%type;
    n int;
    ex_same_location Exception;
    BEGIN
        SELECT Count(id_place) into n FROM Places WHERE latitude = poi_latitude AND longitude=poi_longitude;
        if(n>0) THEN
            raise ex_same_location;
        END IF;
        INSERT INTO Places(latitude,longitude,elevation) VALUES (poi_latitude,poi_longitude,poi_elevation);
        SELECT MAX(id_place) into poi_id_place FROM Places;
        INSERT INTO POI VALUES(poi_id_place,poi_desc);
    Exception
        WHEN ex_same_location THEN
            raise_application_error(-20001, 'Place already inserted in the database');
    END;
    
CREATE OR REPLACE FUNCTION getPOIS RETURN SYS_REFCURSOR AS
    poiList SYS_REFCURSOR;
    BEGIN
        Open poiList for 
        SELECT * FROM POI P, Places Pl WHERE P.id_poi= Pl.id_place;   
        RETURN poiList;
    END;
    	
CREATE OR REPLACE FUNCTION getVehiclesInPark(park_ID int) RETURN SYS_REFCURSOR AS
	curVehicles SYS_REFCURSOR;
	BEGIN
		OPEN curVehicles FOR
							SELECT * 
							FROM vehicles v
							WHERE v.id_park = park_ID ;
		RETURN curVehicles;
	END;

CREATE OR REPLACE PROCEDURE unlockVehicle(vehicle_ID int) IS
	BEGIN 
		UPDATE VEHICLES v 
		SET id_park = 0 
		WHERE v.id_vehicle = vehicle_ID ;
	END;
	
CREATE OR REPLACE PROCEDURE parkVehicle(vehicle_ID int, parkid INT) IS
	BEGIN 
		UPDATE VEHICLES v 
		SET id_park = parkid
		WHERE v.id_vehicle = vehicle_ID ;
	END;
	
CREATE OR REPLACE PROCEDURE updateUnlockTime(vehicle_request_id int, unlock_time_p TIMESTAMP) AS
	BEGIN
		UPDATE VEHICLE_REQUESTS vr
		SET unlock_time = unlock_time_p
		WHERE vr.vehicle_request_id = vehicle_request_id;
	END;
	
CREATE OR REPLACE PROCEDURE updateEndTime(vehicleRequest_id int, ent_time_p TIMESTAMP) AS
	BEGIN
		UPDATE VEHICLE_REQUESTS
		SET unlock_time = ent_time_p
		WHERE vehicle_request_id= vehicleRequest_id;
	END;
	
CREATE OR REPLACE FUNCTION getVehicleRequests RETURN SYS_REFCURSOR AS
	curVehicleReq SYS_REFCURSOR ;
	BEGIN 
		OPEN curVehicleReq FOR 
							SELECT * 
							FROM VEHICLE_REQUESTS;
		RETURN curVehicleReq;
	END;

CREATE OR REPLACE PROCEDURE addRequest(vehicle_request_id INT, user_email VARCHAR, vehicle_ID INT, placeOrigId INT, placeDestID INT, unlock_time TIMESTAMP, end_time TIMESTAMP) AS	
	BEGIN
		INSERT INTO vehicle_requests
			VALUES(vehicle_request_id, user_email, vehicle_ID, placeOrigId, placeDestID, unlock_time, end_time);
	END;
	
CREATE OR REPLACE FUNCTION getScooters(parkID int) RETURN SYS_REFCURSOR AS
	curVehicles SYS_REFCURSOR;
	BEGIN
		OPEN curVehicles FOR
							SELECT * 
							FROM Vehicles v, Scooters S
							WHERE v.id_park = parkID AND v.id_vehicle=s.id_vehicle ;
		RETURN curVehicles;
	END;
	
CREATE OR REPLACE FUNCTION getBicycle(parkID int) RETURN SYS_REFCURSOR AS
	curVehicles SYS_REFCURSOR;
	BEGIN
		OPEN curVehicles FOR
							SELECT * 
							FROM Vehicles v,Bicycles b
							WHERE v.id_park = parkID AND v.id_vehicle=b.id_vehicle ;
		RETURN curVehicles;
	END;
	
CREATE OR REPLACE FUNCTION getPark(latitude_park NUMERIC, longitude_park NUMERIC) RETURN SYS_REFCURSOR IS
    park SYS_REFCURSOR;
    BEGIN
        OPEN park FOR SELECT * FROM PARKS P, Places Pl WHERE Pl.latitude = latitude_park AND Pl.longitude = longitude_park AND Pl.id_place= P.id_park;
        RETURN park;
    END;
	
CREATE OR REPLACE FUNCTION getVehicleRequestByID(vReqID int) RETURN SYS_REFCURSOR AS
	vRequest SYS_REFCURSOR;
	BEGIN
		OPEN vRequest FOR SELECT *
						  FROM VEHICLE_REQUESTS vr
						  WHERE vr.vehicle_request_id = vReqID;
		RETURN vRequest;
	END;

CREATE OR REPLACE PROCEDURE ADDVEHICLE(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE, P_ID_PARK VEHICLES.ID_PARK%TYPE, P_AVAILABLE VEHICLES.AVAILABLE%TYPE, P_WEIGTH VEHICLES.WEIGTH%TYPE, P_PARKLATITUDE VEHICLES.PARKLATITUDE%TYPE, P_PARKLONGITUDE VEHICLES.PARKLONGITUDE%TYPE, P_AERODYNAMIC VEHICLES.AERODYNAMICCOEFFICIENT%TYPE, P_FRONTALAREA VEHICLES.FRONTALAREA%TYPE) AS
BEGIN
    INSERT INTO VEHICLES(ID_VEHICLE, ID_PARK, AVAILABLE, WEIGTH, PARKLATITUDE, PARKLONGITUDE, AERODYNAMICCOEFFICIENT, FRONTALAREA) VALUES (P_ID_VEHICLE, P_ID_PARK, P_AVAILABLE,  P_WEIGTH, P_PARKLATITUDE, P_PARKLONGITUDE, P_AERODYNAMIC, P_FRONTALAREA);
end;

CREATE OR REPLACE PROCEDURE ADDSCOOTER(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE, P_ID_PARK VEHICLES.ID_PARK%TYPE, P_AVAILABLE VEHICLES.AVAILABLE%TYPE, P_WEIGTH VEHICLES.WEIGTH%TYPE, P_PARKLATITUDE VEHICLES.PARKLATITUDE%TYPE, P_PARKLONGITUDE VEHICLES.PARKLONGITUDE%TYPE, P_AERODYNAMIC VEHICLES.AERODYNAMICCOEFFICIENT%TYPE, P_FRONTALAREA VEHICLES.FRONTALAREA%TYPE, P_MAX_BATTERY_LEVEL SCOOTERS.MAX_BATTERY_LEVEL%TYPE, P_ACTUAL_BATTERY_LEVEL SCOOTERS.ACTUAL_BATTERY_LEVEL%TYPE, P_SCOOTER_TYPE SCOOTERS.SCOOTER_TYPE%TYPE) AS
BEGIN
    ADDVEHICLE(P_ID_VEHICLE,P_ID_PARK, P_AVAILABLE, P_WEIGTH, P_PARKLATITUDE, P_PARKLONGITUDE, P_AERODYNAMIC, P_FRONTALAREA);
    INSERT INTO SCOOTERS VALUES (P_ID_VEHICLE, P_MAX_BATTERY_LEVEL, P_ACTUAL_BATTERY_LEVEL, P_SCOOTER_TYPE);
end;

CREATE OR REPLACE PROCEDURE ADDBICYCLE(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE, P_ID_PARK VEHICLES.ID_PARK%TYPE, P_AVAILABLE VEHICLES.AVAILABLE%TYPE, P_WEIGTH VEHICLES.WEIGTH%TYPE, P_PARKLATITUDE VEHICLES.PARKLATITUDE%TYPE, P_PARKLONGITUDE VEHICLES.PARKLONGITUDE%TYPE, P_AERODYNAMIC VEHICLES.AERODYNAMICCOEFFICIENT%TYPE, P_FRONTALAREA VEHICLES.FRONTALAREA%TYPE, P_WHEEL_SIZE BICYCLES.WHEEL_SIZE%TYPE) AS
BEGIN
    ADDVEHICLE(P_ID_VEHICLE,P_ID_PARK, P_AVAILABLE, P_WEIGTH, P_PARKLATITUDE, P_PARKLONGITUDE, P_AERODYNAMIC, P_FRONTALAREA);
    INSERT INTO BICYCLES VALUES (P_ID_VEHICLE, P_WHEEL_SIZE);
end;	

CREATE OR REPLACE PROCEDURE UPDATEVEHICLE(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE, P_ID_PARK VEHICLES.ID_PARK%TYPE, P_WEIGTH VEHICLES.WEIGTH%TYPE, P_PARKLATITUDE VEHICLES.PARKLATITUDE%TYPE, P_PARKLONGITUDE VEHICLES.PARKLONGITUDE%TYPE, P_AERODYNAMIC VEHICLES.AERODYNAMICCOEFFICIENT%TYPE, P_FRONTALAREA VEHICLES.FRONTALAREA%TYPE) AS
BEGIN
        UPDATE VEHICLES SET ID_PARK = P_ID_PARK WHERE ID_VEHICLE = P_ID_VEHICLE AND P_ID_PARK IS NOT NULL;
        UPDATE VEHICLES SET WEIGTH = P_WEIGTH WHERE ID_VEHICLE = P_ID_VEHICLE AND P_WEIGTH IS NOT NULL;
        UPDATE VEHICLES SET PARKLATITUDE = P_PARKLATITUDE WHERE ID_VEHICLE = P_ID_VEHICLE AND P_PARKLATITUDE IS NOT NULL;
        UPDATE VEHICLES SET PARKLONGITUDE = P_PARKLONGITUDE WHERE ID_VEHICLE = P_ID_VEHICLE AND P_PARKLONGITUDE IS NOT NULL;
        UPDATE VEHICLES SET AERODYNAMICCOEFFICIENT = P_AERODYNAMIC WHERE ID_VEHICLE = P_ID_VEHICLE AND P_AERODYNAMIC IS NOT NULL;
        UPDATE VEHICLES SET FRONTALAREA = P_FRONTALAREA WHERE ID_VEHICLE = P_ID_VEHICLE AND P_FRONTALAREA IS NOT NULL;
end;

CREATE OR REPLACE PROCEDURE UPDATESCOOTER(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE, P_ID_PARK VEHICLES.ID_PARK%TYPE, P_WEIGTH VEHICLES.WEIGTH%TYPE, P_PARKLATITUDE VEHICLES.PARKLATITUDE%TYPE, P_PARKLONGITUDE VEHICLES.PARKLONGITUDE%TYPE, P_AERODYNAMIC VEHICLES.AERODYNAMICCOEFFICIENT%TYPE, P_FRONTALAREA VEHICLES.FRONTALAREA%TYPE, P_MAX_BATTERY_LEVEL SCOOTERS.MAX_BATTERY_LEVEL%TYPE, P_ACTUAL_BATTERY_LEVEL SCOOTERS.ACTUAL_BATTERY_LEVEL%TYPE, P_SCOOTER_TYPE SCOOTERS.SCOOTER_TYPE%TYPE) AS
BEGIN
    UPDATEVEHICLE(P_ID_VEHICLE, P_ID_PARK, P_WEIGTH, P_PARKLATITUDE, P_PARKLONGITUDE, P_AERODYNAMIC, P_FRONTALAREA);

    UPDATE SCOOTERS SET MAX_BATTERY_LEVEL = P_MAX_BATTERY_LEVEL WHERE ID_VEHICLE = P_ID_VEHICLE AND P_MAX_BATTERY_LEVEL IS NOT NULL;
    UPDATE SCOOTERS SET ACTUAL_BATTERY_LEVEL = P_ACTUAL_BATTERY_LEVEL WHERE ID_VEHICLE = P_ID_VEHICLE AND P_ACTUAL_BATTERY_LEVEL IS NOT NULL;
    UPDATE SCOOTERS SET SCOOTER_TYPE  = P_SCOOTER_TYPE WHERE SCOOTER_TYPE = P_SCOOTER_TYPE AND P_SCOOTER_TYPE IS NOT NULL;
end;

CREATE OR REPLACE PROCEDURE UPDATEBICYCLE(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE, P_ID_PARK VEHICLES.ID_PARK%TYPE, P_WEIGTH VEHICLES.WEIGTH%TYPE, P_PARKLATITUDE VEHICLES.PARKLATITUDE%TYPE, P_PARKLONGITUDE VEHICLES.PARKLONGITUDE%TYPE, P_AERODYNAMIC VEHICLES.AERODYNAMICCOEFFICIENT%TYPE, P_FRONTALAREA VEHICLES.FRONTALAREA%TYPE, P_WHEEL_SIZE BICYCLES.WHEEL_SIZE%TYPE) AS
BEGIN
    UPDATEVEHICLE(P_ID_VEHICLE, P_ID_PARK, P_WEIGTH, P_PARKLATITUDE, P_PARKLONGITUDE, P_AERODYNAMIC, P_FRONTALAREA);
    UPDATE BICYCLES SET WHEEL_SIZE = P_WHEEL_SIZE WHERE ID_VEHICLE = P_ID_VEHICLE AND P_WHEEL_SIZE IS NOT NULL;
end;

CREATE OR REPLACE PROCEDURE REMOVEVEHICLE(P_ID_VEHICLE VEHICLES.ID_VEHICLE%TYPE) AS
BEGIN
    UPDATE VEHICLES SET AVAILABLE = '0' WHERE ID_VEHICLE = P_ID_VEHICLE;
end;

CREATE OR REPLACE FUNCTION ALLBICYCLES RETURN SYS_REFCURSOR AS
    ALL_BICYCLES SYS_REFCURSOR;
BEGIN
    OPEN ALL_BICYCLES FOR
    SELECT * FROM VEHICLES V, BICYCLES B WHERE B.ID_VEHICLE = V.ID_VEHICLE;
    RETURN ALL_BICYCLES;
end;

CREATE OR REPLACE PROCEDURE updateAvailability(ID_UPDATE VEHICLES.ID_VEHICLE%TYPE) AS
BEGIN
	UPDATE VEHICLES SET AVAILABLE = '1' WHERE ID_VEHICLE = ID_UPDATE;
end;

CREATE OR REPLACE FUNCTION ALLSCOOTERS RETURN SYS_REFCURSOR AS
    ALL_SCOOTERS SYS_REFCURSOR;
BEGIN
    OPEN ALL_SCOOTERS FOR
    SELECT * FROM VEHICLES V, SCOOTERS S WHERE S.ID_VEHICLE = V.ID_VEHICLE;
    RETURN ALL_SCOOTERS;
end;

CREATE OR REPLACE FUNCTION ALLAVAILABLESCOOTERS RETURN SYS_REFCURSOR AS
    ALL_SCOOTERS SYS_REFCURSOR;
BEGIN
    OPEN ALL_SCOOTERS FOR
    SELECT * FROM VEHICLES V, SCOOTERS S WHERE S.ID_VEHICLE = V.ID_VEHICLE AND V.AVAILABLE = '1';
    RETURN ALL_SCOOTERS;
end;

/*Path DB*/
CREATE OR REPLACE PROCEDURE addPath(idPlaceO int, idPlaceD int, kinecticCoeficient NUMERIC, windDor NUMERIC, windSpeed NUMERIC) AS
    ex_same_place Exception;
    n int;
    BEGIN
        if(idPlaceO=idPlaceD) THEN
            raise ex_same_place;
        END IF;
        SELECT count(*) INTO n FROM Paths WHERE id_place_begin=idPlaceO AND id_place_end=idPlaceD;
        if(n!=0) THEN
            raise ex_same_place;
        END IF;
        INSERT INTO PATHS(id_place_begin,id_place_end,kinectic_coeficient,wind_dir,wind_speed) VALUES(idPlaceO,idPlaceD,kinecticCoeficient,windDor,windSpeed);
        
    EXCEPTION
        WHEN ex_same_place THEN
            raise_application_error(-20001, 'Cant create a path with the same places');
    END;
    
CREATE OR REPLACE FUNCTION getPaths RETURN SYS_REFCURSOR AS
    lp SYS_REFCURSOR;
    BEGIN
       OPEN lp FOR
       SELECT * FROM Paths;
       RETURN lp;
    END;
    
CREATE OR REPLACE FUNCTION getPlaceByCoord(p_latitude NUMERIC, p_longitude NUMERIC) RETURN SYS_REFCURSOR AS
    p SYS_REFCURSOR;
    BEGIN
        OPEN p FOR
        SELECT * FROM Places WHERE latitude=p_latitude AND longitude=p_longitude;
        RETURN p;
    END;
    
CREATE OR REPLACE FUNCTION getVehicleRequestsHistory(EMAIL CLIENT.EMAIL%TYPE) RETURN S AS
    VEHICLES_REQUEST SYS_REFCURSOR;
BEGIN
    OPEN VEHICLES_REQUEST FOR
    SELECT CASE WHEN VRP.ORDER = 1 THEN '0' ELSE '1' END, 
           CASE WHEN VRP.ORDER = 1 THEN VR.UNLOCKTIME ELSE VR.ENDTIME END AS REQUEST_DATE, 
           CASE WHEN (SELECT COUNT(*) FROM SCOOTERS WHERE ID_VEHICLE = VR.VEHICLEID) = 1 THEN 'ePT0' || VR.VEHICLEID ELSE 'PT0' || VR.VEHICLEID END,
           PK.DESCRIPTION 
    FROM VEHICLEREQUESTS VR, VEHICLEREQUESTS_PATH VRP, PATHS P, PARKS PK 
    WHERE VR.VEHICLEREQUESTID = VRP.VEHICLEREQUESTID AND 
          VRP.IDPATH = P.IDPATH AND 
          CLIENTEMAIL = EMAIL AND
          (VRP.ORDER = 1 AND P.IDPLACEBEGIN = PK.IDPLACE OR VRP.ORDER = (SELECT MAX(ORDER) FROM VEHICLEREQUESTS_PATH VRP2, VEHICLEREQUESTS VR2 WHERE VRP2.VEHICLEREQUESTID = VRP.VEHICLEREQUESTID AND VRP2.VEHICLEREQUESTID = VR2.VEHICLEREQUESTID AND VR2.CLIENTEMAIL = VR.CLIENTEMAIL) AND P.IDPLACEEND = PK.IDPLACE)
    ORDER BY REQUEST_DATE;
    RETURN VEHICLES_REQUEST;
END;

CREATE OR REPLACE FUNCTION getActiveVehicleRequestsByDate(time Timestamp) RETURN SYS_REFCURSOR AS
	vehicleRequests SYS_REFCURSOR;
	BEGIN
		OPEN vehicleRequests FOR
					SELECT * 
					FROM VEHICLEREQUESTS VR
					WHERE (VR.UNLOCKTIME < time and VR.ENDTIME > time )
					or (VR.UNLOCKTIME < time and VR.ENDTIME is null);
		RETURN vehicleRequests;
	END;

CREATE OR REPLACE PROCEDURE CHARGE_VEHICLES AS
    MAX_CHARGE_PER_POINT INTEGER;
    CHARGE_POWER         PARKS.INPUTVOLTAGE%TYPE;
    CHARGE_PER_POINT     PARKS.INPUTVOLTAGE%TYPE;
    REMAIN_BATTERY       INTEGER;
    TIME_CHARGED         DATE;
    BATTERY_CHARGED      SCOOTERS.MAX_BATTERY_LEVEL%TYPE;
    NEW_ACTUAL_BATTERY   SCOOTERS.ACTUAL_BATTERY_LEVEL%TYPE;
BEGIN
    MAX_CHARGE_PER_POINT := 3000; --W
    SELECT (SYSDATE - NVL(MAX(LAST_CHARGING_UPDATE), SYSDATE)) * 24 * 60 * 60
    INTO TIME_CHARGED
    FROM VEHICLES_CHARGING_HISTORY;

    FOR PARK IN (SELECT ID_PARK,
                        INPUTVOLTAGE,
                        INPUTCURRENT,
                        (SELECT COUNT(*)
                         FROM VEHICLES V,
                              SCOOTERS S
                         WHERE S.ID_VEHICLE = V.ID_VEHICLE
                           AND V.ID_PARK = P.ID_PARK
                           AND ACTUAL_BATTERY_LEVEL < 100) AS N_MAX_SCOOTERS
                 FROM PARKS P)
        LOOP
            CHARGE_POWER := PARK.INPUTVOLTAGE * PARK.INPUTCURRENT; --W = V * A
            CHARGE_PER_POINT := CHARGE_POWER / PARK.N_MAX_SCOOTERS; --W = W / n
            IF (CHARGE_POWER > MAX_CHARGE_PER_POINT) THEN
                CHARGE_POWER := MAX_CHARGE_PER_POINT;
            end if;
            FOR VEHICLE IN (SELECT MAX_BATTERY_LEVEL, ACTUAL_BATTERY_LEVEL, ID_VEHICLE
                            FROM VEHICLES V,
                                 SCOOTERS S
                            WHERE V.ID_VEHICLE = S.ID_VEHICLE
                              AND V.ID_PARK = PARK.ID_PARK
                              AND ACTUAL_BATTERY_LEVEL < 100)
                LOOP
                    REMAIN_BATTERY := (VEHICLE.MAX_BATTERY_LEVEL -
                                       VEHICLE.MAX_BATTERY_LEVEL * (VEHICLE.ACTUAL_BATTERY_LEVEL / 100)) * 1000 *
                                      3600; --JOULES
                    BATTERY_CHARGED := CHARGE_POWER * TIME_CHARGED;
                    REMAIN_BATTERY := REMAIN_BATTERY - BATTERY_CHARGED;
                    IF REMAIN_BATTERY > 0 THEN
                        NEW_ACTUAL_BATTERY := 100 - REMAIN_BATTERY / VEHICLE.MAX_BATTERY_LEVEL * 100;
                    ELSE
                        NEW_ACTUAL_BATTERY := 100;
                    end if;

                    UPDATE SCOOTERS SET ACTUAL_BATTERY_LEVEL = NEW_ACTUAL_BATTERY WHERE ID_VEHICLE = VEHICLE.ID_VEHICLE;
                end loop;

        end loop;
end;

CREATE OR REPLACE FUNCTION getVReqByEmailAndVeh(vehicleID INT, email VARCHAR(80)) RETURN SYS_REFCURSOR AS
	vehicleRequests SYS_REFCURSOR;
	BEGIN
		OPEN vehicleRequests FOR
					SELECT * 
					FROM VEHICLEREQUESTS VR
					WHERE (VR.user_email = email) AND (VR.vehicle_id = vehicleID);
		RETURN vehicleRequests;
	END;
