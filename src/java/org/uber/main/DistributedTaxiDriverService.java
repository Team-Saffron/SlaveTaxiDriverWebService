/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uber.main;

import distributedtaxidriver.Constants.Constants;
import distributedtaxidriver.DataProcessor;
import distributedtaxidriver.KMeansHandlers.KMeansProcessor;
import distributedtaxidriver.OutputHandlers.Logger;
import distributedtaxidriver.POJO.Cluster;
import distributedtaxidriver.POJO.Driver;
import java.util.ArrayList;
/**
 *
 * @author archit08jain
 */
class DistributedTaxiDriverService {
    
    private DataProcessor dataProcessor;
    private Double currentTimeSlot;
    private KMeansProcessor kMeansProcessor;
    private ArrayList<Cluster> clusters;
    
    DistributedTaxiDriverService() {
        clusters = new ArrayList<Cluster>();
        dataProcessor = new DataProcessor();
        kMeansProcessor = new KMeansProcessor();
        currentTimeSlot = -1.0 * Constants.INF;
    }

    public Integer getDestination(String driverPosition, ArrayList<Cluster> clusters) {
                
        Driver driver = new Driver();
       
        driver = dataProcessor.processInput(driverPosition);
  
        Logger.write("Cluster Position:\n" + clusters + "\n");
        
        Integer bestId = dataProcessor.getBestCluster(clusters, driver);
        System.err.println("Result" + bestId + "\n");
        return bestId;
    }    
}
