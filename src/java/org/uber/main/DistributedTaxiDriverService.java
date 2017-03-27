/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uber.main;

import distributedtaxidriver.Constants.Constants;
import distributedtaxidriver.DataProcessor;
import distributedtaxidriver.KMeansHandlers.KMeansProcessor;
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

    public String getDestination(String driverPosition, Double driverRequestTime) {
                
        Driver driver = new Driver();
       
        driver = dataProcessor.processInput(driverPosition);
        
        System.out.println("Currently Processing Client: " 
                + driver.getLatitude() + "," 
                + driver.getLongitude() + "," 
                + driver.getTime());
        
        driver.setTime(driverRequestTime);
        
        if ((driver.getTime() - Constants.REAPPLY_DURATION) > currentTimeSlot) {    
         
            currentTimeSlot = driver.getTime();
            System.out.println("\nRe-applying K-means....\n");
            System.out.println("New time slot: " + currentTimeSlot);
            currentTimeSlot = driver.getTime();
            clusters = kMeansProcessor.getClusters(currentTimeSlot);
            dataProcessor.updateClusters(clusters);
          
        } else {
            System.out.println("\nUsing previous Clusters....");
        }
        Integer bestClusterId = dataProcessor.getBestCluster(clusters, driver);
        
        // Increment number of drivers in cluster
        clusters.get(bestClusterId).setDrivers(clusters.get(bestClusterId).getDrivers() + 1);
        
        return dataProcessor.convertClusterToString(clusters.get(bestClusterId));
    }    
}
