/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehicleRequestRegistry;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author bruno
 */
public class ReceiveUnlockedVehiclesReportControllerTest {
    
    ReceiveUnlockedVehiclesReportController ruvrc;
    VehicleRequestsDB vrdb;
    
    @BeforeEach
    public void setUp() {
        vrdb = mock(VehicleRequestsDB.class);
        ruvrc = new ReceiveUnlockedVehiclesReportController();
        ruvrc.setVRDB(vrdb);
        Set<VehicleRequest> h = new HashSet<>();
        VehicleRequest vReq = new VehicleRequest(1, "soimbra@gmail.com", 1, 14, 17, "16/07/2019 16:38:14", "");
        h.add(vReq);
        when(vrdb.getActiveRequestsGivenTime(anyString())).thenReturn(h);
    }

    /**
     * Test of requestLockingUnlockingHistoryVehicles method, of class RequestLockingUnlockingHistoryVehiclesController.
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    @Test
    public void testGetActiveRequests() throws SQLException, ParseException {
        System.out.println("testGetActiveRequests");
        Set<VehicleRequest> expResult = new HashSet<>();
        VehicleRequest vReq = new VehicleRequest(1, "soimbra@gmail.com", 1, 14, 17, "16/07/2019 16:38:14", "");
        expResult.add(vReq);
        Set<VehicleRequest> result = ruvrc.getActiveRequests("16/07/2019 16:42:05");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
        Date date2 = new Date();
        date=dateFormat.parse("16/07/2019 16:42:05");
        date2=dateFormat.parse(vReq.getUnlockTime());
        long timeDif=(date.getTime()-date2.getTime())/1000;
        System.out.println("timeDif: "+timeDif);
        assertEquals(expResult, result);
    }
    
    @Test public void testGetUnlockingReport() throws ParseException{
        System.out.println("testGetUnlockingReport");
        String result = this.ruvrc.getUnlockingReport("16/07/2019 16:42:05");
        String expResult =  "Vehicle ID: 1\n"
                + "Time of unlocking: 16/07/2019 16:38:14\n"
                + "Unlocker: soimbra@gmail.com\n";
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetVehicleRequestDB() {
        System.out.println("testGetVehicleRequestDB");
        ReceiveUnlockedVehiclesReportController dummy=new ReceiveUnlockedVehiclesReportController(vrdb);
        assertEquals(vrdb, dummy.getVRDB());
    }
}

