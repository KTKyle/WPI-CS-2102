package HW1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Examples {

    @Test
    public void testProgressUntilRecharge50Percent(){
        Copter copter1 = new Copter ("Discovery",
                new Propellers (4,1.0,0.25) ,
                new Battery (200,100),
                200);
        assertEquals(0.5, copter1.progressUntilRecharge(), 0.01);
    }

    @Test
    public void testProgressUntilRecharge100Percent(){
        Copter copter2 = new Copter ("Discovery 2",
                new Propellers (4,2.0,0.5),
                new Battery(4500, 2250),
                2250);
        assertEquals(1.0, copter2.progressUntilRecharge(), 0.01);
    }

    @Test
    public void testcanReachDest(){
        Copter copter3 = new Copter ("Discovery 3",
                new Propellers (4, 10,2.5),
                new Battery(3250, 600),
                450);
        assertEquals(true, copter3.canReachDest());
    }

    @Test
    public void testwhoGoesFurther1(){
        Battery battery1 = new Battery(2000,1000);
        Battery battery2 = new Battery(1750,1000);

        Copter copter4 = new Copter("Discovery 4",
                new Propellers(4,1,0.25),
                battery1,
                10);
        Copter copter5 = new Copter("Discovery 5",
                new Propellers(4,1,0.25),
                battery2,
                10);
        assertEquals("Discovery 4", copter4.whoGoesFurther(copter5));
    }

    @Test
    public void testwhoGoesFurther2(){
        Battery battery1 = new Battery(2000,1000);
        Battery battery2 = new Battery(2000,1000);

        Copter copter6 = new Copter("Discovery 6",
                new Propellers(4,1,0.25),
                battery1,
                10);
        Copter copter7 = new Copter("Discovery 7",
                new Propellers(4,1,0.25),
                battery2,
                10);
        assertEquals("Discovery 6&Discovery 7", copter6.whoGoesFurther(copter7));
    }

    @Test
    public void testwhoGoesFurther3(){
        Battery battery1 = new Battery(2000,1000);
        Battery battery2 = new Battery(3500,1000);

        Copter copter8 = new Copter("Discovery 8",
                new Propellers(4,1,0.25),
                battery1,
                10);
        Copter copter9 = new Copter("Discovery 9",
                new Propellers(4,1,0.25),
                battery2,
                10);
        assertEquals("Discovery 9", copter8.whoGoesFurther(copter9));
    }

    @Test
    public void testtravelFor(){
        Copter copter10 = new Copter("Discovery 10",
                new Propellers(4,100,25),
                new Battery(4500, 2250),
                5000);
        copter10.travelFor(5);
        assertEquals(4500, copter10.metersToDestination,0.01);
        assertEquals(1750, copter10.battery.amountLeft,0.01);
    }
}
