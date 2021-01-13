DROP TABLE CLIENTS              CASCADE CONSTRAINTS PURGE;
DROP TABLE CREDIT_CARDS         CASCADE CONSTRAINTS PURGE;
DROP TABLE VEHICLES             CASCADE CONSTRAINTS PURGE;
DROP TABLE BICYCLES             CASCADE CONSTRAINTS PURGE;
DROP TABLE SCOOTERS             CASCADE CONSTRAINTS PURGE;

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
    CONSTRAINT PK_CLIENTS_EMAIL PRIMARY KEY (EMAIL)
);

CREATE TABLE CREDIT_CARDS(
    EMAIL           VARCHAR(80),
    TIN             INTEGER,
    EXPDATE         VARCHAR(10)             CONSTRAINT NN_CREDIT_CARDS_EXPDATE      NOT NULL,
    CREDITS         NUMERIC(7,2)            CONSTRAINT NN_CREDIT_CARDS_CREDITS      NOT NULL,
                                            CONSTRAINT CK_CREDIT_CARDS_CREDITS      CHECK(CREDITS>0),
    CONSTRAINT PK_CREDIT_CARDS_EMAIL_TIN PRIMARY KEY (EMAIL, TIN)                                   
);

CREATE TABLE VEHICLES(
    ID_VEHICLE                  INTEGER,
    ID_PARK                     INTEGER                                 CONSTRAINT NN_VEHICLES_ID_PARK                          NOT NULL,
    AVAILABLE                   CHAR(1)                                 CONSTRAINT NN_VEHICLES_AVAILABLE                        NOT NULL,
    WEIGTH                      INTEGER                                 CONSTRAINT NN_VEHICLES_WEIGTH                           NOT NULL,
    parkLatitude                FLOAT,
    parkLongitude               FLOAT,
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
    MAX_BATTERY_LEVEL                   FLOAT                                   CONSTRAINT NN_SCOOTERS_MAX_BATTERY_LEVEL                        NOT NULL,
    ACTUAL_BATTERY_LEVEL                FLOAT                                   CONSTRAINT NN_SCOOTERS_ACTUAL_BATTERY_LEVEL                     NOT NULL,
    SCOOTER_TYPE                        VARCHAR(20)                             CONSTRAINT NN_SCOOTERS_SCOOTER_TYPE                             NOT NULL,
    CONSTRAINT PK_SCOOTERS_ID_VEHICLES PRIMARY KEY (ID_VEHICLE)
);

ALTER TABLE VEHICLES        ADD CONSTRAINT CK_VEHICLE_PARK_LATITUDE                      CHECK (PARKlatitude > -90 AND PARKlatitude < 90);
ALTER TABLE VEHICLES        ADD CONSTRAINT CK_VEHICLE_PARK_LONGITUDE                     CHECK (PARKlongitude > -180 AND PARKlongitude < 180);
ALTER TABLE VEHICLES        ADD CONSTRAINT FK_VEHICLES_PARKS_ID_VEHICLE                  FOREIGN KEY (ID_PARK)                   REFERENCES PARKS(IDENTIFICATION);
ALTER TABLE BICYCLES        ADD CONSTRAINT FK_BICYCLES_VEHICLES_ID_VEHICLE               FOREIGN KEY (ID_VEHICLE)                REFERENCES VEHICLES(ID_VEHICLE);
ALTER TABLE SCOOTERS        ADD CONSTRAINT FK_SCOOTERS_VEHICLES_ID_VEHICLE               FOREIGN KEY (ID_VEHICLE)                REFERENCES VEHICLES(ID_VEHICLE);
