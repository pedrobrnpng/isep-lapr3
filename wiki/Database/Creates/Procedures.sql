create or replace FUNCTION getClients RETURN SYS_REFCURSOR AS
    curClients SYS_REFCURSOR;
BEGIN
    OPEN curClients FOR SELECT * FROM Clients;
    RETURN curClients;
END;

create or replace PROCEDURE addClient(emailp Clients.email%type, namep Clients.nameclient%type, passp Clients.pass%type, agep Clients.age%type, heightp Clients.height%type, weightp Clients.weight%type, genderp Clients.gender%type) IS
BEGIN
    INSERT INTO Clients VALUES(emailp, namep, passp, agep, heightp, weightp, genderp, 0);
END;

create or replace PROCEDURE updateAvgCyclingSpeed(emailp Clients.email%type, speedp Clients.avgcyclingspeed%type) AS
BEGIN
    UPDATE Clients
    SET avgcyclingspeed= speedp
    WHERE email=emailp;
END;

create or replace FUNCTION getClient(emailp Clients.email%type) RETURN SYS_REFCURSOR IS
cli SYS_REFCURSOR;
BEGIN
    OPEN cli FOR SELECT * FROM Clients WHERE EMAIL= emailp;
    RETURN cli;
END;

create or replace FUNCTION getCreditCards RETURN SYS_REFCURSOR AS
    curCreditCards SYS_REFCURSOR;
BEGIN
    OPEN curCreditCards FOR SELECT * FROM Credit_Cards;
    RETURN curCreditCards;
END;

create or replace PROCEDURE addCreditCard(emailp Credit_Cards.email%type, tinp Credit_Cards.tin%type, expdatep Credit_Cards.expdate%type, creditsp Credit_Cards.credits%type) IS
BEGIN
    INSERT INTO Credit_Cards VALUES(emailp, tinp, expdatep, creditsp);
END;

create or replace FUNCTION getCreditCard(p_tin Credit_Cards.tin%type) RETURN SYS_REFCURSOR IS
cc SYS_REFCURSOR;
BEGIN
    OPEN cc FOR SELECT * FROM Credit_Cards WHERE TIN= p_tin;
    RETURN cc;
END;
    
create or replace PROCEDURE updateCreditCard(tin1 Credit_Cards.tin%type, expDate1 Credit_Cards.expdate%type, credits1 Credit_Cards.credits%type) AS
BEGIN
    UPDATE Credit_Cards
    SET tin= tin1,
        expDate= expDate1,
        credits= credits1
    WHERE tin=tin1;
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
    SELECT * FROM VEHICLES V WHERE B.ID_VEHICLE = V.ID_VEHICLE;
    RETURN ALL_BICYCLES;
end;

CREATE OR REPLACE FUNCTION ALLSCOOTERS RETURN SYS_REFCURSOR AS
    ALL_SCOOTERS SYS_REFCURSOR;
BEGIN
    OPEN ALL_SCOOTERS FOR
    SELECT * FROM VEHICLES V, SCOOTERS S WHERE S.ID_VEHICLE = V.ID_VEHICLE;
    RETURN ALL_SCOOTERS;
end;

CREATE OR REPLACE PROCEDURE updateAvailability(ID_UPDATE VEHICLES.ID_VEHICLE%TYPE) AS
BEGIN
	UPDATE VEHICLES SET AVAILABLE = '1' WHERE ID_VEHICLE = ID_UPDATE;
end;

CREATE OR REPLACE FUNCTION getActiveVehicleRequestsByDate(time Timestamp) RETURN SYS_REFCURSOR AS
    vehicleRequests SYS_REFCURSOR;
    BEGIN
        OPEN vehicleRequests FOR
                    SELECT * 
                    FROM VEHICLE_REQUESTS VR
                    WHERE (VR.UNLOCK_TIME < time and VR.END_TIME > time )
                    or (VR.UNLOCK_TIME < time and VR.END_TIME is null);
        RETURN vehicleRequests;
    END;