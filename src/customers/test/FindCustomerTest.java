package customers.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customers.FindCustomer;

class FindCustomerTest {

  private double startLat;
  private double startLon;
  private double endLat;
  private double endLon;

  @Test
  public void testDistanceWithEmptyVars() {
    double expResult = 12.34;
    double result = FindCustomer.getDistance(startLat, startLon, endLat, endLon);
    assertNotEquals(expResult, result, 0.0);
  }

  @Test
  public void testDistanceBetweenSourceAndDestination() {
    startLat = 53.138712;
    startLon = -7.059126;
    double expResult = 57.82;
    assertEquals(FindCustomer.getDistance(startLat, startLon, FindCustomer.dLat, FindCustomer.dLon), expResult);
  }

}
