/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uber.main;

import distributedtaxidriver.POJO.Cluster;
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
    
    public Integer getDestinationCoordinates(String driverPosition, ArrayList<Cluster> clusters) {
        return service.getDestination(driverPosition, clusters);
    } 
    
}
