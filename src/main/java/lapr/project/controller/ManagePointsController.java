/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ClientDB;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Client;
import lapr.project.model.Place;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleRequest;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author pedro
 */
public class ManagePointsController {

    private final static String URLDB = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private final static String USERDB = "LAPR3_2019_G022";
    private final static String PASSDB = "qwerty";

    private final static int NOPARK = -15;
    private final static int FIRSTREWARD = 5;
    private final static int FIRSTELEVATION = 25;
    private final static int SECONDREWARD = 15;
    private final static int SECONDELEVATION = 50;
    private final static int TIMEREWARD = 5;
    
    private final Set<Place> placesList;

    private VehicleRequestsDB vehicleRequestDB;
    private VehicleDB vehicleDB;
    private ClientDB cliDB;
    private ParkDB parkDB;
    private POIDB poiD;

    public ManagePointsController() {
        parkDB = new ParkDB(URLDB, USERDB, PASSDB);
        vehicleDB = new VehicleDB(URLDB, USERDB, PASSDB);
        vehicleRequestDB = new VehicleRequestsDB(URLDB, USERDB, PASSDB);
        cliDB = new ClientDB(URLDB, USERDB, PASSDB);
        poiD = new POIDB(URLDB, USERDB, PASSDB);
        placesList = new HashSet<>();
    }

    public void setDB(VehicleRequestsDB vr, ClientDB c, VehicleDB v, ParkDB p, POIDB poi) {
        this.vehicleRequestDB = vr;
        this.cliDB = c;
        this.vehicleDB = v;
        this.parkDB = p;
        this.poiD = poi;
    }

    public void checkParking(int vReqID) throws InvalidInfoClientException, SQLException {
        placesList.addAll(parkDB.getExistingParks());
        placesList.addAll(poiD.getExistingPOI());

        int points = 0;
        VehicleRequest vReq = vehicleRequestDB.getVehicleRequest(vReqID);
        Client client = cliDB.getClient(vReq.getUserEmail());

        int vId = vReq.getVehicleID();
        HashSet<Vehicle> vehicles = vehicleDB.getAllVehicles();

        Vehicle vehicle = null;
        for (Vehicle v : vehicles) {
            if (v.getID() == vId) {
                vehicle = v;
            }
        }

        int idPark = vehicle.getIDPark();

        if (idPark == 0) {
            points += NOPARK;
        }

        int destID = vReq.getDestinationID();
        int origID = vReq.getOriginID();

        Place orig = null;
        Place dest = null;
        for (Place p : placesList) {
            if (p.getId() == destID) {
                dest = p;
            }
            if (p.getId() == origID) {
                orig = p;
            }
        }

        double elevation = Math.abs(dest.getElevation() - orig.getElevation());
        if (elevation >= SECONDELEVATION) {
            points += SECONDREWARD;
        } else if (elevation >= FIRSTELEVATION) {
            points += FIRSTREWARD;
        }

        if (vReq.tripTimeReward()) {
            points += TIMEREWARD;
        }
        client.addPoints(points);
        cliDB.updatePoints(client.getEmail(), points);
    }

}
