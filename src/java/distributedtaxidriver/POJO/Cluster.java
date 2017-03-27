/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.POJO;

/**
 * Cluster POJO class to keep details related to Cluster.
 * Used for hypothesis calculations.
 * @author Abhishek
 */
public class Cluster {

    public Double getCrowdDensity() {
        return crowdDensity;
    }

    public void setCrowdDensity(Double crowdDensity) {
        this.crowdDensity = crowdDensity;
    }

    public Double getCrowdCount() {
        return crowdCount;
    }

    public void setCrowdCount(Double crowdCount) {
        this.crowdCount = crowdCount;
    }

    public Integer getDrivers() {
        return drivers;
    }

    public void setDrivers(Integer drivers) {
        this.drivers = drivers;
    }

    public Double getCentroidLatitude() {
        return centroidLatitude;
    }

    public void setCentroidLatitude(Double centroidLatitude) {
        this.centroidLatitude = centroidLatitude;
    }

    public Double getCentroidLongitude() {
        return centroidLongitude;
    }

    public void setCentroidLongitude(Double centroidLongitude) {
        this.centroidLongitude = centroidLongitude;
    }
    @Override
    public String toString() {
        return "Cluster{" + "lat=" + centroidLatitude 
                + ", lon=" + centroidLongitude 
                + ", drivers=" + drivers
                + ", densty=" + crowdDensity
                + ", Crowd=" + crowdCount
                + "}";
    }
    private Double crowdDensity;
    private Double crowdCount;
    private Integer drivers;
    private Double centroidLatitude;
    private Double centroidLongitude;
}
