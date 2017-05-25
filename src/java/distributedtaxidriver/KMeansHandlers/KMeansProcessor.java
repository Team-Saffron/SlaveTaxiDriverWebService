/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.KMeansHandlers;

import distributedtaxidriver.KMeansHandlers.KMeans;
import distributedtaxidriver.Constants.Constants;
import distributedtaxidriver.DataProcessor;
import distributedtaxidriver.POJO.Cluster;
import distributedtaxidriver.POJO.DataPoint;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhishek
 */
public class KMeansProcessor {
    
    ArrayList<DataPoint> data;
    DataProcessor dataProcessor;
    KMeans kMeansObject;
   // ServerOutputManager outputManager = ServerOutputManager.getSingletonInstance();
    
    public KMeansProcessor() {
        dataProcessor = new DataProcessor();
        kMeansObject  = new KMeans();
    }
    public ArrayList<Cluster> getClusters(Double timeSlot) {
        ArrayList<Cluster> results;
        ArrayList<DataPoint> clusters;
        ArrayList<Integer> crowdAtEachCluster;
        ArrayList<Double> densityAtEachCluster;
        
        loadData();
        
        dataProcessor.getCustData(timeSlot + 5, data);
        clusters = kMeansObject.KMeansAlgo(data, Constants.NUMBER_OF_CLUSTERS);
        crowdAtEachCluster = kMeansObject.calculateCrowd();
        densityAtEachCluster = kMeansObject.calculateDensity();
        
        results = packResults(clusters, crowdAtEachCluster, densityAtEachCluster);
        return results;
    }
    
    ArrayList<Cluster> packResults(ArrayList<DataPoint> clusters, ArrayList<Integer> crowdAtEachCluster, ArrayList<Double> densityAtEachCluster) {
        ArrayList<Cluster> result = new ArrayList<>();
        //outputManager.write("Packing resultant clusters....", Color.green);
        Integer numberOfClusters = clusters.size();
        
        if (crowdAtEachCluster.size() != numberOfClusters || densityAtEachCluster.size() != numberOfClusters) {
           // System.err.println("Number of clusters mismatch!!!\n Aborting!!");
        } else {
            for (int i = 0; i < numberOfClusters; i++) {
                Cluster tempCluster = new Cluster();
                tempCluster.setCentroidLatitude(clusters.get(i).getLat());
                tempCluster.setCentroidLongitude(clusters.get(i).getLon());
                tempCluster.setCrowdCount((double)crowdAtEachCluster.get(i));
                tempCluster.setCrowdDensity(densityAtEachCluster.get(i));
                tempCluster.setDrivers(0);
                
                result.add(tempCluster);
            }
        }
        return result;
    }
    private void loadData() {
        data = new ArrayList();
        DataPoint temp;   
        
        //Load Data From DataSet
        String FileName = Constants.DATAFILE;
        
        Scanner freader = null;
        try {
            freader = new Scanner(new File(FileName));
        
            while(freader.hasNextDouble())
            {
                temp = new DataPoint();
                temp.setTime(freader.nextDouble());
                temp.setLat(freader.nextDouble());
                temp.setLon(freader.nextDouble());

                data.add(temp);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KMeansProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            freader.close();
        }
    }
}
