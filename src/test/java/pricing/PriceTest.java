package pricing;

import org.junit.Assert;
import org.junit.Test;

import static dataObject.StopID.STOP1;
import static dataObject.StopID.STOP2;
import static dataObject.StopID.STOP3;
import static pricing.PricingTable.getFee;

public class PriceTest {

    private final Double expected12 = 3.25;
    private final Double expected23 = 5.50;
    private final Double expected13 = 7.30;


    @Test
    public void testStop1to2() {
        Double actual12 = getFee(STOP1, STOP2);
        Double actual21 = getFee(STOP2, STOP1);

        Assert.assertEquals(expected12, actual12);
        Assert.assertEquals(expected12, actual21);
    }

    @Test
    public void testStop2to3() {
        Double actual23 = getFee(STOP2, STOP3);
        Double actual32 = getFee(STOP3, STOP2);

        Assert.assertEquals(expected23, actual23);
        Assert.assertEquals(expected23, actual32);
    }

}
