/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedtaxidriver.MapsHandlers;

import distributedtaxidriver.POJO.DataPoint;

/**
 *
 * @author Abhishek
 */
public class MapNode {
    double sLat,sLon,dLat,dLon;
    public MapNode(double sLat,double sLon,double dLat,double dLon)
    {
        this.sLat = sLat;
        this.sLon = sLon;
        this.dLat = dLat;
        this.dLon = dLon;
    }
    public MapNode(DataPoint d1,DataPoint d2)
    {
        this.sLat = d1.getLat();
        this.sLon = d1.getLon();
        this.dLat = d2.getLat();
        this.dLon = d2.getLon();
    }

    @Override
    public String toString() {
        return "MapNode{" + "sLat=" + sLat + ", sLon=" + sLon + ", dLat=" + dLat + ", dLon=" + dLon + '}';
    }   
}
