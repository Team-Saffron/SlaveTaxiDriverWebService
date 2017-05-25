/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uber.main;

import distributedtaxidriver.OutputHandlers.Logger;
import distributedtaxidriver.POJO.Cluster;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author archit08jain
 */
@WebService(serviceName = "DistributedTaxiDriver")
public class DistributedTaxiDriver {

    private DistributedTaxiDriverService service  = new DistributedTaxiDriverService();
    
    /*public String getDestinationCoordinates(String driverPosition, Double driverRequestTime) {
        return service.getDestination(driverPosition,driverRequestTime);
    }*/
    
    public DistributedTaxiDriver() {
        Logger.write("Initializing service....\n") ;
    }
    public Integer getDestinationCoordinates(String driverPosition, ArrayList<Cluster> clusters) {
        Logger.write("Request received from: " + driverPosition);
        Logger.write("Request received at: " + LocalDateTime.now() + "\n\n");
        return service.getDestination(driverPosition, clusters);
    } 
    
}
