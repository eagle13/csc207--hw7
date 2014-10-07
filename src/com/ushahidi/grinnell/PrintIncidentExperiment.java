package com.ushahidi.grinnell;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiTestingClient;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class PrintIncidentExperiment
{

  public static void main(String[] args) 
      throws Exception
    {
      // Create the output device
      PrintWriter pen = new PrintWriter(System.out, true);

      // A few basic incidents
      //UshahidiExtensions.printIncident(pen, UshahidiUtils.SAMPLE_INCIDENT);
      //UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());
      //UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());

      // A newly created incident
      //UshahidiIncident myIncident = new UshahidiIncident(37, "sample");
      //UshahidiExtensions.printIncident(pen, myIncident);

      ArrayList<UshahidiIncident> incidentArray = new ArrayList<UshahidiIncident>();
      UshahidiTestingClient client;

      for (int i = 0; i < 12; i++)
        {
          incidentArray.add(UshahidiUtils.randomIncident());
        }
      
      client = new UshahidiTestingClient(incidentArray);
      
      // One from a list
      //UshahidiClient webClient = UshahidiUtils.SAMPLE_CLIENT;
      //UshahidiExtensions.printIncident(pen, webClient.nextIncident());

      //UshahidiExtensions.extremeIncidents(client);
      // One that requires connecting to the server
      UshahidiClient webclient = new UshahidiWebClient("http://ushahidi1.grinnell.edu/sandbox");
      //Vector<UshahidiIncident> locationVector = UshahidiExtensions.selectLocation(webclient, 41.748, -92.721, 5);
      ArrayList<UshahidiIncident> array = UshahidiExtensions.selectIncidents(webclient, LocalDateTime.of(2014, 9, 15, 16, 37), LocalDateTime.now());
      for (UshahidiIncident incident : array)
        {
          UshahidiExtensions.printIncident(pen, incident);
        }
      //UshahidiExtensions.printIncident(pen, webclient.nextIncident());
    } // main(String[])

}
