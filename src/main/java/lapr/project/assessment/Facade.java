package lapr.project.assessment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.pow;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.controller.AddParksController;
import lapr.project.controller.AddPathController;
import lapr.project.controller.AddVehiclesController;
import lapr.project.controller.ChangeParkAvailabilityController;
import lapr.project.controller.CheckInvoiceController;
import lapr.project.controller.CheckNearestParksController;
import lapr.project.controller.CheckParkAvailabilityController;
import lapr.project.controller.CheckParkVehiclesController;
import lapr.project.controller.CheckUserInfoController;
import lapr.project.controller.EndUseVehicleController;
import lapr.project.controller.GetParkReportController;
import lapr.project.controller.LoadPOIController;
import lapr.project.controller.ManagePointsController;
import lapr.project.controller.MostEnergyEfficientPathPOIController;
import lapr.project.controller.ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController;
import lapr.project.controller.ReceiveUnlockedVehiclesReportController;
import lapr.project.controller.RegisterUserController;
import lapr.project.controller.RemoveParksController;
import lapr.project.controller.RequestVehicleController;
import lapr.project.controller.SeeParksController;
import lapr.project.controller.ShortestPathController;
import lapr.project.data.DataHandler;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.POI;
import lapr.project.model.Park;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Place;
import lapr.project.model.Scooter;
import lapr.project.model.RoutesPerVehicle;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleRequest;
import lapr.project.model.WriteReport;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import lapr.project.utils.comparatorBicycles;
import lapr.project.utils.comparatorScooters;
import lapr.project.utils.maths.Mathematics;
import static lapr.project.utils.maths.Mathematics.calculateGeoDistance;
import lapr.project.utils.maths.Physics;

/**
 *
 * @author bruno
 */
public class Facade implements Serviceable {

    private final CheckParkVehiclesController checkParkVehiclesController = new CheckParkVehiclesController();
    private final SeeParksController seeParksController = new SeeParksController();
    private final MostEnergyEfficientPathPOIController mostEnergyEfficientPathPOIController = new MostEnergyEfficientPathPOIController();
    private final ChangeParkAvailabilityController changeParkAvailabilityController = new ChangeParkAvailabilityController();
    private final AddParksController addParksController = new AddParksController();
    private final RegisterUserController registerUserController = new RegisterUserController();
    private final CheckNearestParksController checkNearestParksController = new CheckNearestParksController();
    private final CheckUserInfoController checkUserInfoController = new CheckUserInfoController();
    private final CheckInvoiceController checkInvoiceController = new CheckInvoiceController();
    private final AddVehiclesController addVehiclesController = new AddVehiclesController();
    private final LoadPOIController loadPOIController = new LoadPOIController();
    private final AddPathController addPathController = new AddPathController();
    private final ShortestPathController shortestPathController = new ShortestPathController();
    private final RemoveParksController removeParksController = new RemoveParksController();
    private final GetParkReportController getParkReportControllerR = new GetParkReportController();
    private final ReceiveUnlockedVehiclesReportController receiveUnlockedVehiclesReportController = new ReceiveUnlockedVehiclesReportController();
    private final EndUseVehicleController endUseVehicleController = new EndUseVehicleController();
    private final RequestVehicleController reqVehicleController = new RequestVehicleController();
    private final ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController receiveMostEfficientRoute = new ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController();
    private final CheckParkAvailabilityController cpAvailabilityController = new CheckParkAvailabilityController();
    /**
     *
     */
    public Facade() {
        DataHandler dh = new DataHandler("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        try {
            dh.scriptRunner("../lapr3-2019-g022/target/classes/drop.sql");
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dh.scriptRunner("../lapr3-2019-g022/target/classes/create.sql");
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int addBicycles(String s) {
        File parks = new File(s);
        int count = 0;
        try (Scanner scanner = new Scanner(parks)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                int id = Integer.getInteger(line[0].replaceAll("PT0", ""));
                int weigth = Integer.getInteger(line[1]);
                double parkLatitude = Double.valueOf(line[2]);
                double parkLongitude = Double.valueOf(line[3]);
                double aerodynamicCoefficient = Double.valueOf(line[4]);
                double frontalArea = Double.valueOf(line[5]);
                String wheelSize = line[6];
                int idPark = addVehiclesController.getParkByCoordinates(parkLatitude, parkLongitude);
                if (addVehiclesController.addBicycle(wheelSize, id, idPark, weigth, aerodynamicCoefficient, frontalArea)) {
                    count++;
                } else {
                    return 0;
                }
            }
            addVehiclesController.registerBicycles();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int addEscooters(String s) {
        File parks = new File(s);
        int count = 0;
        try (Scanner scanner = new Scanner(parks)) {
            while (scanner.hasNextLine()) {
                String[] text = scanner.nextLine().split(";");
                int id = Integer.getInteger(text[0].replaceAll("ePT0", ""));
                int weigth = Integer.getInteger(text[1]);
                String type = text[2];
                double parkLatitude = Double.valueOf(text[3]);
                double parkLongitude = Double.valueOf(text[4]);
                int maxBatteryCapacity = Integer.getInteger(text[5]);
                int actualBatteryCapacity = Integer.getInteger(text[6]);
                double aerodynamicCoefficient = Double.valueOf(text[7]);
                double frontalArea = Double.valueOf(text[8]);
                int motor = Integer.getInteger(text[9]);
                int idPark = addVehiclesController.getParkByCoordinates(parkLatitude, parkLongitude);
                if (addVehiclesController.addScooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weigth, aerodynamicCoefficient, frontalArea, motor)) {
                    count++;
                } else {
                    return 0;
                }
            }
            addVehiclesController.registerScooters();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int addParks(String s) {
        File parks = new File(s);
        int count = 0;

        try (Scanner sc = new Scanner(parks)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.charAt(0) != '#') {
                    break;
                }
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(";");
                addParksController.newPark(words[0].trim(), Double.parseDouble(words[1].trim()), Double.parseDouble(words[2].trim()), Double.parseDouble(words[3].trim()), words[4].trim(), Integer.parseInt(words[5].trim()), Integer.parseInt(words[6].trim()), Double.parseDouble(words[7].trim()), Double.parseDouble(words[8].trim()));
                count++;
            }
            count = addParksController.addAllParks();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int addPOIs(String s) {
        File pois = new File(s);
        int count = 0;
        try (Scanner scPOI = new Scanner(pois)) {
            while (scPOI.hasNextLine()) {
                String line = scPOI.nextLine();
                if (line.charAt(0) != '#') {
                    break;
                }
            }
            while (scPOI.hasNextLine()) {
                String line = scPOI.nextLine();
                String[] words = line.split(";");
                loadPOIController.newPOI(Double.parseDouble(words[0].trim()), Double.parseDouble(words[1].trim()), Double.parseDouble(words[2].trim()), words[3].trim());
            }
            count = loadPOIController.addPOIlist();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int addUsers(String s) {
        File users = new File(s);
        int count = 0;
        try (Scanner sc = new Scanner(users)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.charAt(0) != '#') {
                    break;
                }
            }
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] words = line.split(";");
                registerUserController.newClient(words[0].trim(), words[1].trim(), words[2].trim(), Integer.valueOf(words[3].trim()), Double.valueOf(words[4].trim()), Double.valueOf(words[5].trim()), words[6].trim().toCharArray()[0]);
            }
            count = registerUserController.registerAllClients();
        } catch (FileNotFoundException | InvalidInfoClientException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int addPaths(String s) {
        File paths = new File(s);
        int count = 0;
        try (Scanner scPath = new Scanner(paths)) {
            while (scPath.hasNextLine()) {
                String line = scPath.nextLine();
                if (line.charAt(0) != '#') {
                    break;
                }
            }
            while (scPath.hasNextLine()) {
                String line = scPath.nextLine();
                String[] words = line.split(";");
                addPathController.newPath(Double.parseDouble(words[0].trim()), Double.parseDouble(words[1].trim()), Double.parseDouble(words[2].trim()), Double.parseDouble(words[3].trim()), Double.parseDouble(words[4].trim()), Double.parseDouble(words[5].trim()), Double.parseDouble(words[6].trim()));
            }
            count = addPathController.addAllPaths();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param v
     * @param v1
     * @param s
     * @return
     */
    @Override
    public int getNumberOfBicyclesAtPark(double v, double v1, String s) {
        int number = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s))) {

            List<Park> parkList = checkNearestParksController.getNearestParks(v, v1, checkNearestParksController.getAllParks(), 0.01);
            if (!parkList.isEmpty()) {
                Park park = parkList.get(0);
                Set<Bicycle> bicycleList = checkParkVehiclesController.getExistingBicyclesByParkID(park.getId());
                List<Bicycle> list = new ArrayList<>(bicycleList);
                Collections.sort(list, new comparatorBicycles());
                Iterator<Bicycle> iterator = list.iterator();
                while (iterator.hasNext()) {
                    number++;
                    Bicycle next = iterator.next();
                    writer.write(next.getID() + ";" + next.getWheelSize() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return number;
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public int getNumberOfBicyclesAtPark(String string, String string1) {
        int number = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string1))) {

            int id_park = Integer.parseInt(string);
            Set<Bicycle> bicycleList = checkParkVehiclesController.getExistingBicyclesByParkID(id_park);
            List<Bicycle> list = new ArrayList<>(bicycleList);
            Collections.sort(list, new comparatorBicycles());
            Iterator<Bicycle> iterator = list.iterator();
            while (iterator.hasNext()) {
                number++;
                Bicycle next = iterator.next();
                writer.write(next.getID() + "," + next.getWheelSize() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * @param d latitude
     * @param d1 longitude
     * @param string file name
     *
     */
    @Override
    public void getNearestParks(double d, double d1, String string) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string));) {
            checkNearestParksController.getRegistries();
            Set<Park> parkList = checkNearestParksController.getAllParks();
            List<Park> nearestParks = new ArrayList<>();
            nearestParks.addAll(checkNearestParksController.getNearestParks(d, d1, parkList));
            if (!nearestParks.isEmpty()) {
                Iterator<Park> iterator = nearestParks.iterator();
                while (iterator.hasNext()) {
                    Park next = iterator.next();
                    writer.write(next.getLatitude() + ";" + next.getLongitude() + ";" + Math.round(Mathematics.calculateGeoDistance(d, next.getLatitude(), d1, next.getLongitude()) * 1000) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public int removePark(String string) {
        removeParksController.getPark(string);
        return removeParksController.deactivatePark() ? 1 : 0;
    }

    /**
     *
     * @param d latitude
     * @param d1 longitude
     * @param string outputfile
     * @param i radius
     */
    @Override
    public void getNearestParks(double d, double d1, String string, int i) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string));) {

            checkNearestParksController.getRegistries();
            Set<Park> parkList = checkNearestParksController.getAllParks();
            List<Park> nearestParks = new ArrayList<>();
            nearestParks.addAll(checkNearestParksController.getNearestParks(d, d1, parkList, i));
            if (!nearestParks.isEmpty()) {
                Iterator<Park> iterator = nearestParks.iterator();
                while (iterator.hasNext()) {
                    Park next = iterator.next();
                    writer.write(next.getLatitude() + ";" + next.getLongitude() + ";" + Math.round(Mathematics.calculateGeoDistance(d, next.getLatitude(), d1, next.getLongitude()) * 1000) + "\n");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public int getFreeBicycleSlotsAtPark(String string, String string1) {
        return cpAvailabilityController.getFreeSlots(string, string);
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public int getFreeEscooterSlotsAtPark(String string, String string1) {
        return cpAvailabilityController.getFreeSlots(string, string1);
    }

    /**
     *
     * @param d
     * @param d1
     * @param d2
     * @param d3
     * @return
     */
    @Override
    public int linearDistanceTo(double d, double d1, double d2, double d3) {
        return (int) calculateGeoDistance(d, d1, d2, d3);
    }

    /**
     *
     * @param d
     * @param d1
     * @param d2
     * @param d3
     * @return
     */
    @Override
    public int pathDistanceTo(double d, double d1, double d2, double d3) {
        return this.shortestPathController.pathDistanceTO(d, d1, d2, d3, new LinkedList<Place>());
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public long unlockBicycle(String string, String string1) {
        try {
            reqVehicleController.unlockVehicle(Integer.valueOf(string), string1);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public long unlockEscooter(String string, String string1) {
        try {
            reqVehicleController.unlockVehicle(Integer.valueOf(string), string1);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string ID
     * @param d parkLat
     * @param d1 parkLong
     * @param string1 username
     * @return
     */
    @Override
    public long lockBicycle(String string, double d, double d1, String string1) {
        int bikeID = Integer.parseInt(string);
        
        try {
            endUseVehicleController.newEndUseLocation(bikeID, d, d1, string1);
            endUseVehicleController.endUse();
            changeParkAvailabilityController.updateAvailability(bikeID);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string ID
     * @param string1 parkID
     * @param string2 username
     * @return
     */
    @Override
    public long lockBicycle(String string, String string1, String string2) {
        int bikeID = Integer.parseInt(string);
        
        try {
            endUseVehicleController.newEndUse(bikeID, string1, string2);
            endUseVehicleController.endUse();
            changeParkAvailabilityController.updateAvailability(bikeID);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string ID
     * @param d parkLat
     * @param d1 parkLong
     * @param string1 username
     * @return
     */
    @Override
    public long lockEscooter(String string, double d, double d1, String string1) {
        int bikeID = Integer.parseInt(string);
        
        try {
            endUseVehicleController.newEndUseLocation(bikeID, d, d1, string1);
            endUseVehicleController.endUse();
            changeParkAvailabilityController.updateAvailability(bikeID);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string ID
     * @param string1 parkID
     * @param string2 username
     * @return
     */
    @Override
    public long lockEscooter(String string, String string1, String string2) {
        int bikeID = Integer.parseInt(string);
        
        try {
            reqVehicleController.unlockVehicle(Integer.valueOf(string), string1);
            changeParkAvailabilityController.updateAvailability(bikeID);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string
     * @param string1
     * @param d
     * @param d1
     * @param string2
     * @return
     */
    @Override
    public int suggestEscootersToGoFromOneParkToAnother(String string, String string1, double d, double d1, String string2) {
        int nScooter = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string2))) {
            Set<Scooter> nVehicles = reqVehicleController.getAvailableScooter(string, d1, d1);
            for (Scooter s : nVehicles) {
                nScooter++;

                writer.write(s.getDescription() + ";" + s.getWeight() + ";" + s.getType() + ";"
                        + reqVehicleController.getPark(s.getIDPark()).getLatitude()
                        + ";" + reqVehicleController.getPark(s.getIDPark()).getLongitude()
                        + ";" + s.getMaxBatteryCapacity() + ";" + s.getActualBatteryCapacity()
                        + ";" + s.getAerodynamicCoefficient()
                        + ";" + s.getFrontalArea() + ";" + s.getMotor());
                writer.newLine();

            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nScooter;
    }

    /**
     *
     * @param string
     * @param string1
     * @param string2
     * @param string3
     * @param string4
     * @param string5
     * @return
     */
    @Override
    public long mostEnergyEfficientRouteBetweenTwoParks(String string, String string1, String string2, String string3, String string4, String string5) {
        List<RoutesPerVehicle> routes = receiveMostEfficientRoute.receiveMostEnergeticallyRouteBetweenTwoParks(string4, Integer.parseInt(string), Integer.parseInt(string1));
        receiveMostEfficientRoute.getRoutesOfTypeVehicle(string2, string3, routes);
        long dist = (long) receiveMostEfficientRoute.getRouteDistance(routes.get(0).getRoutes());
        int i = 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string5))) {
            for (RoutesPerVehicle rpv : routes) {
                String s = "";
                for (Place places : rpv.getRoutes()) {
                    s += places.getLatitude() + ";" + places.getLongitude() + "\n";
                }
                writer.append("Path 00" + i + "\ntotal_distance:" + receiveMostEfficientRoute.getRouteDistance(rpv.getRoutes()) + "\ntotal_energy:" + rpv.getWeigth() + "\nelevation:" + receiveMostEfficientRoute.getElevation(rpv) + "\n" + s);
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(WriteReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dist;
    }

    /**
     *
     * @param d latitude of park
     * @param d1 longitude of park
     * @param string output name
     * @return
     */
    @Override
    public int getNumberOfEscootersAtPark(double d, double d1, String string) {
        int number = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string))) {

            List<Park> parkList = checkNearestParksController.getNearestParks(d, d1, checkNearestParksController.getAllParks(), 0.01);
            if (!parkList.isEmpty()) {
                Park park = parkList.get(0);
                Set<Scooter> bicycleList = checkParkVehiclesController.getExistingScootersByParkID(park.getId());
                List<Scooter> list = new ArrayList<>(bicycleList);
                Collections.sort(list, new comparatorScooters());
                Iterator<Scooter> iterator = list.iterator();
                while (iterator.hasNext()) {
                    number++;
                    Scooter next = iterator.next();
                    writer.write(next.getID() + ";" + next.getType() + ";" + next.getActualBatteryCapacity() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return number;
    }

    /**
     *
     * @param string id of park
     * @param string1 output name
     * @return
     */
    @Override
    public int getNumberOfEScootersAtPark(String string, String string1) {
        int number = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string1))) {

            int id_park = Integer.parseInt(string);
            Set<Scooter> bicycleList = checkParkVehiclesController.getExistingScootersByParkID(id_park);
            List<Scooter> list = new ArrayList<>(bicycleList);
            Collections.sort(list, new comparatorScooters());
            Iterator<Scooter> iterator = list.iterator();
            while (iterator.hasNext()) {
                number++;
                Scooter next = iterator.next();
                writer.write(next.getID() + ";" + next.getType() + ";" + next.getActualBatteryCapacity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public int getFreeSlotsAtParkForMyLoanedVehicle(String string, String string1) {
        return cpAvailabilityController.getFreeSlots(string, string1);
    }

    @Override
    public int registerUser(String string, String string1, String string2, String string3, int i, int i1, BigDecimal bd, String string4) {
        int count = 0;
        try {
            registerUserController.newClient(string, string1, string2, Integer.valueOf(string3), i, i1, string4.charAt(0));
            if (registerUserController.registerClient()) {
                count = 1;
            }
        } catch (InvalidInfoClientException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     *
     * @param string
     * @param string1
     * @param string2
     * @return
     */
    @Override
    public long unlockAnyEscooterAtPark(String string, String string1, String string2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string2))) {
            Scooter s = reqVehicleController.getAvailableVehicleMost(string, string);
            writer.write(s.getDescription() + ";" + s.getWeight() + ";" + s.getType() + ";" 
                        + reqVehicleController.getPark(s.getIDPark()).getLatitude() + 
                        ";" + reqVehicleController.getPark(s.getIDPark()).getLongitude() + 
                        ";" + s.getMaxBatteryCapacity() + ";" + s.getActualBatteryCapacity() + 
                        ";" + s.getAerodynamicCoefficient() + 
                        ";" + s.getFrontalArea() + ";" + s.getMotor());
                writer.newLine();
        } catch (InvalidInfoClientException | SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
    }

    /**
     *
     * @param string
     * @param string1
     * @param d
     * @param d1
     * @param string2
     * @return
     */
    @Override
    public long unlockAnyEscooterAtParkForDestination(String string, String string1, double d, double d1, String string2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string2))) {
            Scooter s = reqVehicleController.unlockAScooterAtParkForDestination(string, string1, d, d1);
            writer.write(s.getDescription() + ";" + s.getWeight() + ";" + s.getType() + ";" 
                        + reqVehicleController.getPark(s.getIDPark()).getLatitude() + 
                        ";" + reqVehicleController.getPark(s.getIDPark()).getLongitude() + 
                        ";" + s.getMaxBatteryCapacity() + ";" + s.getActualBatteryCapacity() + 
                        ";" + s.getAerodynamicCoefficient() + 
                        ";" + s.getFrontalArea() + ";" + s.getMotor());
                writer.newLine();
        } catch (InvalidInfoClientException | SQLException | IOException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Instant.now().toEpochMilli();
   }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public double getUserCurrentDebt(String string, String string1) {
        double debt = -1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string1));) {
            try {
                addInvoicesToUser(string, -1, "2019-12-05", 12, 20, 12);
                addInvoicesToUser(string, -2, "2019-11-05", 11, 1, 10);
                addInvoicesToUser(string, -3, "2019-10-05", 10, 0, 13);
                debt = checkUserInfoController.getUserDebt(string);
            } catch (Exception ex) {
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.write("Debt of user " + string + ": " + debt);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return debt;
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public double getUserCurrentPoints(String string, String string1) {
        double points = -1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string1));) {
            try {
                if (checkUserInfoController.getClient(string)) {
                    Client cli = checkUserInfoController.getClient();
                    points = cli.getPoints();
                }
            } catch (InvalidInfoClientException ex) {
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.write("Points of user " + string + ": " + points);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return points;
    }

    /**
     *
     * @param d lat1
     * @param d1 long1
     * @param d2 lat2
     * @param d3 long2
     * @param string username
     * @return
     */
    @Override
    public double calculateElectricalEnergyToTravelFromOneLocationToAnother(double d, double d1, double d2, double d3, String string) {
        Set<Park> parks = seeParksController.getExistingParks();
        Park orig = null;
        Park dest = null;
        Set<Client> users = mostEnergyEfficientPathPOIController.getClientRegistry().getClients();
        Client user = null;
        for (Client c : users) {
            if (c.getEmail().equals(string)) {
                user = c;
            }
        }
        if (user != null) {
            for (Park p : parks) {
                if (p.getLatitude() == d && p.getLongitude() == d1) {
                    orig = p;
                }
                if (p.getLatitude() == d2 && p.getLongitude() == d3) {
                    dest = p;
                }
            }
            Set<List<Path>> l = new HashSet<>();
            List<String> report = new LinkedList<>();
            shortestPathController.createShortestPath(orig.getIdentification(), dest.getIdentification(), l, report);
            double energy = Physics.calculateEnergy(user, l.iterator().next().get(0), orig, dest, new Bicycle("19", 1, 1, 20, 0.5, 0.5));
            return energy * 2.77777778 * pow(10, 2);
        }
        return 0;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public long forHowLongAVehicleIsUnlocked(String string) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Date date2 = new Date();
        int vehicID = Integer.parseInt(string);
        VehicleRequestsDB vrdb = receiveUnlockedVehiclesReportController.getVRDB();
        Set<VehicleRequest> vehicReqs = vrdb.getActiveRequestsGivenTime(dateFormat.format(date));
        if (!vehicReqs.isEmpty()) {
            for (VehicleRequest vehic : vehicReqs) {
                if (vehic.getVehicleID() == vehicID) {
                    try {
                        date2 = dateFormat.parse(vehic.getUnlockTime());
                        long timeDif = Math.abs((date.getTime() - date2.getTime()) / 1000);
                        return timeDif;
                    } catch (ParseException ex) {
                        Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(String string, String string1, String string2, String string3) {
        List<Place> l = new ArrayList<>();
        try (Scanner c = new Scanner(new File(string2))) {
            while (c.hasNextLine()) {
                String line = c.nextLine();
                if (line.charAt(0) != '#') {
                    break;
                }
            }
            while (c.hasNextLine()) {
                String line = c.nextLine();
                String[] words = line.split(";");
                l.add(new POI(Double.parseDouble(words[0].trim()), Double.parseDouble(words[1].trim()), Double.parseDouble(words[2].trim()), words[3].trim()));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        Place[] elements = new Place[l.size()];
        int i = 0;
        for (Place p : l) {
            elements[i] = p;
            i++;
        }
        List<String> report = new ArrayList<>();
        long dist = shortestPathController.createShortestWithPOI(string, string1, elements, report);
        WriteReport.writeParkReport(report, string3);
        return dist;
    }

    /**
     *
     * @param d
     * @param d1
     * @param d2
     * @param d3
     * @param string
     * @param string1
     * @return
     */
    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(double d, double d1, double d2, double d3, String string, String string1) {
        List<Place> l = new ArrayList<>();
        try (Scanner s = new Scanner(new File(string))) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.charAt(0) != '#') {
                    break;
                }
            }
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] words = line.split(";");
                l.add(new POI(Double.parseDouble(words[0].trim()), Double.parseDouble(words[1].trim()), Double.parseDouble(words[2].trim()), words[3].trim()));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        Place[] elements = new Place[l.size()];
        int i = 0;
        for (Place p : l) {
            elements[i] = p;
            i++;
        }
        List<String> report = new ArrayList<>();
        long dist = shortestPathController.createShortestWithPOI(d, d1, d2, d3, elements, report);
        WriteReport.writeParkReport(report, string1);
        return dist;
    }

    /**
     *
     * @param string
     * @param string1
     * @return
     */
    @Override
    public long getParkChargingReport(String string, String string1) {
        return getParkReportControllerR.getParkReport(string, string1);
    }

    /**
     *
     * @param string
     * @param string1
     * @param string2
     * @param string3
     * @param string4
     * @param i
     * @param bln
     * @param string5
     * @param string6
     * @param string7
     * @return
     */
    public int suggestRoutesBetweenTwoLocations(String string, String string1, String string2, String string3, String string4, int i, boolean bln, String string5, String string6, String string7) {
        try {
            int idOrig = Integer.parseInt(string);
            int idDest = Integer.parseInt(string1);

            Bicycle bicycle = new Bicycle("19", 1, 1, 20, 0.5, 0.5);
            Scooter scooter = new Scooter("city", 1000, 1000, 1, 1, 35, 0.6, 0.6, 20);
            if (string2.toLowerCase().equals("bicycle")) {
                bicycle.setWheelSize(string3);
            } else if (string2.toLowerCase().equals("scooter")) {
                scooter.setType(string3);
            }
            Client client = new Client("temp", "temp@email.com", "password", 19, 1.77, 75, 'M');
            Set<Client> allClients = mostEnergyEfficientPathPOIController.getClientRegistry().getClients();
            for (Client clientA : allClients) {
                if (clientA.getName().toLowerCase().equals(string4)) {
                    client = clientA;
                }
            }
            Place[] elements;
            File pois = new File(string6);
            Set<Place> poi = new HashSet<>();
            int count = 0;
            try (Scanner scPOI = new Scanner(pois)) {
                while (scPOI.hasNextLine()) {
                    String line = scPOI.nextLine();
                    if (line.charAt(0) != '#') {
                        break;
                    }
                }
                while (scPOI.hasNextLine()) {
                    String line = scPOI.nextLine();
                    String[] words = line.split(";");
                    poi.add(new POI(Double.parseDouble(words[0].trim()), Double.parseDouble(words[1].trim()), Double.parseDouble(words[2].trim()), words[3].trim()));
                }
                elements = (Place[]) poi.toArray();
                Set<List<Path>> path = mostEnergyEfficientPathPOIController.createShortestWithPOI(client, idOrig, idDest, bln, elements, bicycle);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(string5))) {
                String s = "";
                writer.append("Path 001" + i + "\ntotal_distance:" + "\ntotal_energy:" + "\nelevation:" + "\n" + s);
                i++;

            } catch (IOException ex) {
                Logger.getLogger(WriteReport.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 1;

        } catch (InvalidInfoClientException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    /**
     *
     * @param i
     * @param string
     * @param string1
     * @return
     */
    @Override
    public double getInvoiceForMonth(int i, String string, String string1) {
        double cost = -1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(string1));) {
            try {
                addInvoicesToUser(string, -1, "2019-12-05", 12, 20, 12);
                addInvoicesToUser(string, -2, "2019-11-05", i, 1, 10);
                addInvoicesToUser(string, -3, "2019-10-05", 10, 0, 13);
                if (checkInvoiceController.getInvoiceMonthEmail(i, string)) {
                    cost = checkInvoiceController.getInvoice().getTotalCost();
                }
            } catch (Exception ex) {
                Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.write("Cost of invoice from month" + i + ": " + cost);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cost;
    }

    @Override
    public long shortestRouteBetweenTwoParks(double d, double d1, double d2, double d3, int i, String string) {
        List<String> report = new ArrayList<>();
        Set<List<Path>> lpaths = new HashSet<>();
        double dist = shortestPathController.createShortestPath(d, d1, d2, d3, lpaths, report);
        WriteReport.writeParkReport(report, string);
        return (long) dist;
    }

    @Override
    public long shortestRouteBetweenTwoParks(String string, String string1, int i, String string2) {
        List<String> report = new ArrayList<>();
        Set<List<Path>> lpaths = new HashSet<>();
        double dist = shortestPathController.createShortestPath(string, string1, lpaths, report);
        WriteReport.writeParkReport(report, string2);
        return (long) dist;
    }

    private boolean addInvoicesToUser(String email, int id, String date, int period, int usedPoints, double totalCost) throws ParseException {
        try {
            checkInvoiceController.addInvoice(id, date, period, usedPoints, totalCost, email);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
