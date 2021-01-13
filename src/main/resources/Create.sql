/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Utilizador
 * Created: 10/dez/2019
 */
CREATE TABLE CLIENTS(
    EMAIL           VARCHAR(80),
    NAMECLIENT      VARCHAR(50)             CONSTRAINT NN_CLIENTS_NAME              NOT NULL,
    PASS            INTEGER                 CONSTRAINT NN_CLIENTS_PASS              NOT NULL,
    AGE             INTEGER                 CONSTRAINT CK_CLIENTS_AGE               CHECK(AGE>0)
                                            CONSTRAINT NN_CLIENTS_AGE               NOT NULL,
    HEIGHT          NUMERIC(3, 2)           CONSTRAINT CK_CLIENTS_HEIGHT            CHECK(HEIGHT>0)
                                            CONSTRAINT NN_CLIENTS_HEIGHT            NOT NULL,
    WEIGHT          NUMERIC(5, 2)           CONSTRAINT CK_CLIENTS_WEIGHT            CHECK(WEIGHT>0)
                                            CONSTRAINT NN_CLIENTS_WEIGHT            NOT NULL,
    GENDER          CHAR(1)                 CONSTRAINT CK_CLIENTS_GENDER            CHECK(REGEXP_LIKE(GENDER, '^(M|F)$')),
    AVGCYCLINGSPEED NUMERIC(4, 2),
    POINTS          INTEGER                 CONSTRAINT CK_CLIENTS_POINTS            CHECK(POINTS>-1),
    CONSTRAINT PK_CLIENTS_EMAIL PRIMARY KEY (EMAIL)
);

CREATE TABLE CREDIT_CARDS(
    TIN             INTEGER,
    EMAIL           VARCHAR(80)             CONSTRAINT UK_CREDIT_CARDS_EMAIL        UNIQUE,
    EXPDATE         CHAR(10)                CONSTRAINT NN_CREDIT_CARDS_EXPDATE      NOT NULL,
    CREDITS         NUMERIC(7,2)            CONSTRAINT NN_CREDIT_CARDS_CREDITS      NOT NULL,
                                            CONSTRAINT CK_CREDIT_CARDS_CREDITS      CHECK(CREDITS>=0),
    CONSTRAINT PK_CREDIT_CARDS_EMAIL_TIN PRIMARY KEY (TIN)                                   
);

CREATE TABLE PLACES(
    id_place      INTEGER GENERATED AS IDENTITY,
    latitude      NUMERIC(12,8)      CONSTRAINT NN_PLACE_LATITUDE    NOT NULL,
    longitude     NUMERIC(12,8)      CONSTRAINT NN_PLACE_LONGITUDE   NOT NULL,
    elevation     NUMERIC(12,8),
    CONSTRAINT PK_PLACE PRIMARY KEY(id_place)
);

ALTER TABLE PLACES ADD CONSTRAINT CK_PARK_LATITUDE CHECK (LATITUDE > -90 AND LATITUDE < 90);
ALTER TABLE PLACES ADD CONSTRAINT CK_PARK_LONGITUDE CHECK (LONGITUDE > -180 AND LONGITUDE < 180);

CREATE TABLE PARKS(
     id_park                        INTEGER,
	 IDENTIFICATION                 VARCHAR(28)  CONSTRAINT UK_PARK_IDENTIFICATION UNIQUE,
	 DESCRIPTION                    VARCHAR(50)  NOT NULL,
	 MAXNUMBERBICYCLES              INT NOT NULL,
	 MAXNUMBERSCOOTER               INT NOT NULL,
	 INPUTVOLTAGE                   Numeric(5,2) NOT NULL,
	 INPUTCURRENT                   NUMERIC(5,2) NOT NULL,
	 ACTIVE                         CHAR(1)      NOT NULL,
	 CONSTRAINT PK_PARK PRIMARY KEY (id_park)
);

CREATE TABLE POI(
    id_poi INTEGER,
    description VARCHAR(28) CONSTRAINT NN_POI_DESCRIPTION NOT NULL,
    CONSTRAINT PK_POI PRIMARY KEY(id_poi)
);

CREATE TABLE PATHS (
	path_id						INTEGER GENERATED AS IDENTITY,
	id_place_begin              INTEGER,
    id_place_end                INTEGER,
	kinectic_coeficient			NUMERIC(5,3) NOT NULL
                                                CONSTRAINT CK_PATHS_KINECTIC_COEFICIENT                         CHECK(KINECTIC_COEFICIENT BETWEEN 0 AND 1),
	wind_dir					NUMERIC(5,2) NOT NULL
                                                        CONSTRAINT CK_PATHS_WIND_DIR                            CHECK(WIND_DIR BETWEEN 0 AND 360),
	wind_speed					NUMERIC(10,4) NOT NULL,
	CONSTRAINT PK_PATHS PRIMARY KEY (path_id)
);
CREATE TABLE VEHICLES(
    ID_VEHICLE                  INTEGER,
    ID_PARK                     INTEGER                                 CONSTRAINT NN_VEHICLES_ID_PARK                          NOT NULL,
    AVAILABLE                   CHAR(1)                                 CONSTRAINT NN_VEHICLES_AVAILABLE                        NOT NULL,
    WEIGTH                      INTEGER                                 CONSTRAINT NN_VEHICLES_WEIGTH                           NOT NULL,
    aerodynamicCoefficient      FLOAT                                   CONSTRAINT NN_VEHICLES_AERODYNAMIC                      NOT NULL,
    frontalArea                 FLOAT                                   CONSTRAINT NN_VEHICLES_FRONTALAREA                      NOT NULL,
    CONSTRAINT PK_VEHICLES_ID_VEHICLE PRIMARY KEY (ID_VEHICLE)
);

CREATE TABLE BICYCLES(
    ID_VEHICLE                  INTEGER                                 CONSTRAINT NN_BICYCLES_ID_VEHICLE                               NOT NULL,
    WHEEL_SIZE                  VARCHAR(10)                             CONSTRAINT NN_BICYCLES_SIZE                                     NOT NULL,
    CONSTRAINT PK_BICYCLES_ID_VEHICLES PRIMARY KEY (ID_VEHICLE)
);

CREATE TABLE SCOOTERS(
    ID_VEHICLE                          INTEGER                                 CONSTRAINT NN_SCOOTERS_ID_VEHICLE                               NOT NULL,
    MAX_BATTERY_LEVEL                   INTEGER                                 CONSTRAINT NN_SCOOTERS_MAX_BATTERY_LEVEL                        NOT NULL,
    ACTUAL_BATTERY_LEVEL                INTEGER                                 CONSTRAINT NN_SCOOTERS_ACTUAL_BATTERY_LEVEL                     NOT NULL,
    SCOOTER_TYPE                        VARCHAR(20)                             CONSTRAINT NN_SCOOTERS_SCOOTER_TYPE                             NOT NULL,
    motorPower                          FLOAT                                   CONSTRAINT NN_VEHICLES_MOTORPOWER                               NOT NULL,
    CONSTRAINT PK_SCOOTERS_ID_VEHICLES PRIMARY KEY (ID_VEHICLE)
);

CREATE TABLE VEHICLE_REQUESTS ( 
	vehicle_request_id			INTEGER,
	user_email					VARCHAR(80),
	vehicle_id					INTEGER,
	placeOrigID					INTEGER,
	placeDestID					INTEGER,
	unlock_time					TIMESTAMP,
	end_time					TIMESTAMP,
	CONSTRAINT PK_VEHICLE_REQUESTS PRIMARY KEY (vehicle_request_id)
);

CREATE TABLE VehicleRequests_Path(
    vehicle_request_id  INTEGER,
    id_path             INTEGER,
    path_order          INTEGER GENERATED AS IDENTITY,
    CONSTRAINT PK_VehicleRequests_Path PRIMARY KEY(vehicle_request_id,id_path)
);

CREATE TABLE Invoice(
    id_invoice      INTEGER,
    email           VARCHAR(80),
    date_invoice    CHAR(10)        CONSTRAINT NN_INVOICE_DATE         NOT NULL,
    period          INTEGER         CONSTRAINT NN_INVOICE_PERIOD       NOT NULL,
    used_points     INTEGER         CONSTRAINT NN_INVOICE_USED_POINTS  NOT NULL,
    total_cost      NUMERIC(7,2)    CONSTRAINT NN_INVOICE_TOTAL_COST   NOT NULL,
    CONSTRAINT PK_INVOICE PRIMARY KEY(id_invoice)
);

CREATE TABLE Invoice_line(
    id_invoice              INTEGER,
    vehicle_request_id      INTEGER,
    cost                    NUMERIC(7,2)  CONSTRAINT NN_INVOICE_LINE_COST NOT NULL,
    CONSTRAINT PK_INVOICE_LINE PRIMARY KEY(id_invoice,vehicle_request_id)
);


CREATE TABLE VEHICLES_CHARGING_HISTORY(
    LAST_CHARGING_UPDATE            DATE
);

ALTER TABLE PARKS ADD CONSTRAINT FK_PARKS_ID_PLACE FOREIGN KEY(id_park) REFERENCES Places(id_place);
ALTER TABLE POI ADD CONSTRAINT FK_POI_ID_PLACE FOREIGN KEY(id_poi) REFERENCES PLACEs(id_place);
ALTER TABLE PATHS ADD CONSTRAINT FK_PATH_ID_PLACE_BEGIN FOREIGN KEY(id_place_begin) REFERENCES PLACEs(id_place);
ALTER TABLE PATHS ADD CONSTRAINT FK_PATH_ID_PLACE_END FOREIGN KEY(id_place_end) REFERENCES PLACEs(id_place);
ALTER TABLE Vehicles ADD CONSTRAINT FK_VEHICLES_id_park FOREIGN KEY(id_park) REFERENCES Parks(id_park);
ALTER TABLE SCOOTERS ADD CONSTRAINT FK_SCOOTERS_id_vehicle FOREIGN KEY (id_vehicle) REFERENCES Vehicles(id_vehicle);
ALTER TABLE BICYCLES ADD CONSTRAINT FK_BICYCLES_ID_VEHICLE FOREIGN KEY (id_vehicle) REFERENCES Vehicles(id_vehicle);
ALTER TABLE CREDIT_CARDS ADD CONSTRAINT FK_CREDIT_CARDS_EMAIL FOREIGN KEY (email) REFERENCES Clients(email); 
ALTER TABLE Vehicle_Requests ADD CONSTRAINT FK_VEHICLE_REQUESTS_VEHICLE_ID FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id_vehicle);
ALTER TABLE Vehicle_Requests ADD CONSTRAINT FK_VEHICLE_REQUESTS_CLIENT FOREIGN KEY (user_email) REFERENCES Clients(email);
ALTER TABLE Invoice ADD CONSTRAINT FK_INVOICE_EMAIL FOREIGN KEY (email) REFERENCES Clients(email);
ALTER TABLE Invoice_Line ADD CONSTRAINT FK_INVOICE_LINE_ID_INVOICE FOREIGN KEY (id_invoice) REFERENCES Invoice(id_invoice);
ALTER TABLE INVOICE_LINE ADD CONSTRAINT FK_INVOICE_LINE_VEHICLE_REQUEST_ID FOREIGN KEY (vehicle_request_id) REFERENCES Vehicle_Requests(vehicle_request_id);
ALTER TABLE VehicleRequests_Path ADD CONSTRAINT FK_VehicleRequests_Path_VEHICLE_REQUEST_ID FOREIGN KEY (vehicle_request_id) REFERENCES Vehicle_Requests(vehicle_request_id);
ALTER TABLE VehicleRequests_Path ADD CONSTRAINT FK_VehicleRequests_Path_ID_PATH FOREIGN KEY(id_path) REFERENCES Paths(path_id);