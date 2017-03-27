package distributedtaxidriver.MapsHandlers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MapEngine {
    
    private JSONObject Obj;
    private int type;
    private double time,dist;
    private String srcAddress, destAddress,polyLine;
    private MapNode node;
    
    public MapEngine(MapNode m)
    {
         node = m;
         time = -1;
         dist = -1;
         srcAddress = destAddress = null;
        
    }

    private void setTimeAndDistance()
    {
        Obj =  MapUtility.requestJSON(node, 0);
        JSONArray destination_address = (JSONArray)Obj.get("destination_addresses");
        JSONArray origin_address = (JSONArray)Obj.get("origin_addresses");
        
        JSONArray rows = (JSONArray)Obj.get("rows");
        srcAddress = (String) origin_address.get(0);
        destAddress = (String) destination_address.get(0);

        JSONObject elements = (JSONObject)rows.get(0);
 
        JSONArray innerElements  = (JSONArray)elements.get("elements");
        JSONObject aux = (JSONObject)innerElements.get(0);
 
        JSONObject duration = (JSONObject)aux.get("duration");
        JSONObject distance = (JSONObject)aux.get("distance");
        
       // time = Double.parseDouble(duration.get("value").toString())/60;
       // dist= Double.parseDouble(distance.get("value").toString());
    }
    
    private void setPolyLine()
    {
        Obj =  MapUtility.requestJSON(node, 1);
        JSONArray routes = (JSONArray)Obj.get("routes");
        JSONObject temp = (JSONObject)routes.get(0);
        JSONObject pl = (JSONObject)temp.get("overview_polyline");
        polyLine = pl.get("points").toString();
    }
    
    //InterFacing Functions
    public double getTime()
    {
        if(time==-1)
            setTimeAndDistance();
        return time;
    }
    
    public double getDistance()
    {
        if(dist==-1)
            setTimeAndDistance();
        return dist;
    }
    public String getSource()
    {
        if(srcAddress==null)
            setTimeAndDistance();
        return srcAddress;
    }
    public String getDestination()
    {
        if(destAddress==null)
            setTimeAndDistance();
        return destAddress;
    }
    public String getPolyLine()
    {
        if(polyLine==null)
            setPolyLine();
        return polyLine;
    }
    public void makeMap()
    {
        MapUtility.makeMap(node,2,getPolyLine());
    } 
    public String displayMap() throws Exception
    {
        String url  = "https://www.google.co.in/maps/dir/"
                + node.sLat + "," + node.sLon + "/" + node.dLat +"," + node.dLon +"/"
                + "data=!3m1!1e3!4m13!4m12!1m5!1m1!1s0x0:0x0!2m2!1d-122.431297!2d37.773972"
                + "!1m5!1m1!1s0x0:0x0!2m2!1d-122.431297!2d37.773972?hl=en";
        
       // java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        return url;
    }
}
