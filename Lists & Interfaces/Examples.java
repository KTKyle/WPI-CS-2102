package HW2;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Examples {

    //Competition Tests
    Copter copter1 = new Copter("Hornet",
            new Propellers(4,1,0.25),
            new Battery(1,1),
            50);
    Copter copter2 = new Copter("Falcon",
            new Propellers(4,0.4,0.1),
            new Battery(2,2),
            100);
    Copter copter3 = new Copter("Eagle",
            new Propellers(4,1,0.25),
            new Battery(1,1),
            50);
    Copter copter4 = new Copter("Penguin",
            new Propellers(4,4,1),
            new Battery(2,2),
            100);
    Copter copter5 = new Copter("Herring",
            new Propellers(4,4,1),
            new Battery(2000,1950),
            1);

    List<Vehicle> vehicles = List.of(copter1, copter2);
    Competition comp = new Competition(vehicles);

    List<Vehicle> vehicles2 = List.of(copter1, copter4);
    Competition comp2 = new Competition(vehicles2);

    List<Vehicle> vehicles3 = List.of(copter1, copter3);
    Competition comp3 = new Competition(vehicles3);

    List<Vehicle> vehicles4 = List.of();
    Competition comp4 = new Competition(vehicles4);

    List<Vehicle> vehicles5 = List.of(copter1, copter2, copter3, copter4, copter5);
    Competition comp5 = new Competition(vehicles5);

    @Test
    public void testwhoGoesFurthestAtFullChargeCopter(){
        assertEquals("Falcon",comp.whoGoesFurthestAtFullCharge());
    }

    @Test
    public void testwhoGoesFurthestAtFullChargeCopter2(){
        assertEquals("Penguin",comp2.whoGoesFurthestAtFullCharge());
    }

    @Test
    public void testwhoGoesFurthestAtFullChargeCopterEquals(){
        assertEquals("Hornet",comp3.whoGoesFurthestAtFullCharge());
    }

    @Test
    public void testwhoGoesFurthestAtFullChargeCopterNone(){
        assertEquals("No Competitors",comp4.whoGoesFurthestAtFullCharge());
    }

    @Test
    public void testclosestProgressToRecharge(){
        assertEquals(0.02,comp.closestProgressUntilRecharge(),0.01);
    }

    @Test
    public void testallWhoReachDest(){
        assertEquals(Collections.emptyList(),comp.allWhoReachDest());
    }

    @Test
    public void testallWhoReachDest2(){
        assertEquals(List.of("Herring"),comp5.allWhoReachDest());
    }

    @Test
    public void testtravelFor(){
        copter1.battery.amountLeft = 25;
        copter1.metersToDestination = 250;
        comp.travelFor(10);
        assertEquals(copter1.battery.amountLeft, copter1.battery.amountLeft, 0.01);
        assertEquals(copter1.metersToDestination,copter1.metersToDestination,0.01);
    }

    //Rover Tests
    Rover rover1 = new Rover("Rover 1", "USA", new Chassis(100.0, List.of(new Wheel(0.5), new Wheel(0.6))), new Battery(100, 100), List.of(10.0, 20.0, 30.0));
    Rover rover2 = new Rover("Rover 2", "USA", new Chassis(150.0, List.of(new Wheel(0.6), new Wheel(0.7))), new Battery(100, 100), List.of(15.0, 25.0, 35.0));
    Rover rover3 = new Rover("Rover 1", "USA", new Chassis(100.0, List.of(new Wheel(5), new Wheel(5))), new Battery(100, 50), List.of(10.0, 20.0, 30.0));
    List<Vehicle> vehicles6 = List.of(rover1, rover2);
    Competition comp6 = new Competition(vehicles6);
    List<Vehicle> vehicles7 = List.of(rover1, copter1);
    Competition comp7 = new Competition(vehicles7);
    @Test
    public void testWhoGoesFurthestAtFullChargeRover() {
        assertEquals("USA:Rover 1", comp6.whoGoesFurthestAtFullCharge());
    }

    @Test
    public void testProgressUntilRechargeRover() {
        assertEquals(1.0, rover3.progressUntilRecharge(), 0.01); // Assuming the rover can complete the journey at full charge
    }

    @Test
    public void testCanReachDestRover() {
        assertTrue(rover1.canReachDest());
    }

    @Test
    public void testTravelForRover() {
        rover1.travelFor(5);
        assertEquals(0, rover1.battery.amountLeft, 0.01);
        assertEquals(List.of(9.725, 20.0, 30.0),rover1.waypoints);
    }

    //Boat Tests
    Boat boat1 = new Boat("Sea Dog", new Battery(1000, 920), 5.0, 2.0, 100.0);
    Boat boat2 = new Boat("Ocean Cat", new Battery(1000, 920), 10.0, 2.0, 500.0);
    Boat boat3 = new Boat("Water Bird", new Battery(1000, 200), 5.0, 2.0, 90.0);

    @Test
    public void testBoatTravel10() {

        boat1.travelFor(10);
        assertEquals(50.0, boat1.distanceToDestination, 0.01);
        assertEquals(820, boat1.battery.amountLeft, 0.01);
    }

    @Test
    public void testBoatTravel20(){
        boat1.travelFor(20);
        assertEquals(0.0, boat1.distanceToDestination, 0.01);
        assertEquals(200, boat1.battery.amountLeft, 0.01);
    }

    @Test
    public void testDistancePossible() {
        assertEquals(500.0, boat2.distancePossible(), 0.01);
    }

    @Test
    public void testCanReachDest() {
        assertTrue(boat3.canReachDest());
    }
}
