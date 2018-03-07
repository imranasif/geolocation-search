package customers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FindCustomer {
  
  public static final double dLat = 53.339428;
  public static final double dLon = -6.257664;
  public static final int distnace = 100;
  public static final String input_file = "customer_location.txt";

  public static void main(String[] args) {

    JSONArray customers = readFile(input_file);
    
    List<Customer> nearByCustomers = new ArrayList<>();

    for (int i = 0; i < customers.length(); i++) {   
      JSONObject customerObject = customers.optJSONObject(i);
      double sLat = customerObject.optDouble("latitude");
      double sLon = customerObject.optDouble("longitude");
      double dis= getDistance(sLat, sLon, dLat, dLon);
      if(dis <= distnace) {
        Customer customer = new Customer();
        customer.setUserId(customerObject.optInt("user_id"));
        customer.setName(customerObject.optString("name"));
        nearByCustomers.add(customer);  
      }
    }
    Collections.sort(nearByCustomers, (u1, u2) -> u1.userId - u2.userId);
    System.out.println(nearByCustomers);

  }

  @SuppressWarnings({"resource"})
  public static JSONArray readFile(String filename) {
    JSONArray jsonArray = new JSONArray();
    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line = br.readLine();
      while (line != null) {
        jsonArray.put(new JSONObject(line));
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return jsonArray;
  }
  
  public static double getDistance(double slat, double slon, double dlat, double dLon) {

    final int R = 6371; // earth's radius

    double latDistance = Math.toRadians(dlat - slat);
    double lonDistance = Math.toRadians(dLon - slon);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(slat)) * Math.cos(Math.toRadians(dlat))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c;
    return Math.round(distance * 100.0) / 100.0;
  }

}
