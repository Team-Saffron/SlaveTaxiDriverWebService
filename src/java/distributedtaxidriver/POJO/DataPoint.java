/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.POJO;

/**
 *
 * @author Abhishek
 */
public class DataPoint {
    private Double lat;
    private Double lon;
    private Double time;

    public DataPoint() {
        lat = 0.0;
        lon = 0.0;
        time = 0.0;
    }

    
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public static double dist(DataPoint a,DataPoint b) {
       double res;
       res = Math.pow((a.lat - b.lat),2) + Math.pow(a.lon - b.lon,2);
       return res;
    }
}
