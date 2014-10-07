package com.ushahidi.grinnell;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiTestingClient;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;

public class UshahidiExtensions
{
  public static void printIncident(PrintWriter pen, UshahidiIncident incident)
  {
    pen.println("Incident #: " + incident.getTitle());
    pen.println("\t" + incident.getDescription());
    pen.println("\tLocation: " + incident.getLocation());
    pen.println("\tStatus: (" + incident.getMode() + ", "
                + incident.getActive() + ", " + incident.getVerified() + ")");
  }//printIncidents(pen, incident)

  public static void UshahidiTestingClient()
  {
    ArrayList<UshahidiIncident> incidentArray = new ArrayList<UshahidiIncident>();
    UshahidiTestingClient client;

    for (int i = 0; i < 12; i++)
      {
        incidentArray.add(UshahidiUtils.randomIncident());
      }
    
    client = new UshahidiTestingClient(incidentArray);
  }//UshahidiTestingClient()
  
  public static void extremeIncidents(UshahidiClient client) 
      throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    UshahidiIncident tmp = client.nextIncident();
    UshahidiIncident max = tmp;
    UshahidiIncident min = tmp;
    
    while (client.hasMoreIncidents())
      {
        if (tmp.getId() > max.getId())
          {
            max = tmp;
          }
        else if (tmp.getId() < min.getId())
          {
            min = tmp;
          }
        tmp = client.nextIncident();
      }
    pen.println(max.toString());
    pen.println(min.toString());
  }
  
  public static void identifyIncidents(UshahidiClient client, LocalDateTime start, LocalDateTime end) 
      throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    UshahidiIncident tmp = client.nextIncident();
    
    while (client.hasMoreIncidents())
      {
        if (tmp.getDate().isBefore(end) && tmp.getDate().isAfter(start))
          {
            pen.println(tmp.toString());
          }
        tmp = client.nextIncident();
      }
  }//identifyIncidents(client, start, end)
  
  public static ArrayList<UshahidiIncident> selectIncidents(UshahidiClient client, LocalDateTime start, LocalDateTime end) 
      throws Exception
  {
    ArrayList<UshahidiIncident> incidentArray = new ArrayList<UshahidiIncident>();
    UshahidiIncident tmp = client.nextIncident();
    while (client.hasMoreIncidents())
      {
        if (tmp.getDate().isBefore(end) && tmp.getDate().isAfter(start))
          {
            incidentArray.add(tmp);
          }
        tmp = client.nextIncident();
      }
    return incidentArray;
  }
  
  public static Vector<UshahidiIncident> selectLocation(UshahidiClient client, double latitude, double longitude, double distance) 
      throws Exception
  {
    UshahidiIncident tmp = client.nextIncident();
    Vector<UshahidiIncident> incidentVector = new Vector<UshahidiIncident>();
    while (client.hasMoreIncidents())
      {
        if (distance > sqrt(pow((latitude - tmp.getLocation().getLatitude()), 2) +
                             pow((longitude - tmp.getLocation().getLongitude()), 2)))
          {
            incidentVector.add(tmp);
          }
        tmp = client.nextIncident();
      }
    return incidentVector;
  }
}

