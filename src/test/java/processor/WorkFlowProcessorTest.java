package processor;

import dataObject.ResultRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class WorkFlowProcessorTest {

    WorkFlowProcessor workFlow = new WorkFlowProcessor();

    @Test // to test for multiple ON without OFF. The trip should be incomplete with undefined time but positive fee
    public void test1() throws IOException {

        final String test1 = "src/test/resources/data/test1";
//    ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
//    1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
//    3, 22-01-2018 18:00:00, ON, Stop2, Company1, Bus37, 5500005555555559
//    5, 22-01-2018 13:01:00, ON, Stop1, Company1, Bus38, 5500005555555561
//    6, 22-01-2018 13:00:00, ON, Stop2, Company1, Bus38, 5500005555555560
//    8, 22-01-2019 14:00:00, ON, Stop1, Company1, Bus38, 5500005555555562

        List<ResultRecord> results = workFlow.process(test1);

        Assert.assertEquals(5, results.size());

        for (ResultRecord resultRecord : results) {

            Assert.assertEquals("UNDEFINED", resultRecord.getFinished());
            Assert.assertEquals("UNDEFINED", resultRecord.getDurationSecs());
            Assert.assertEquals("INCOMPLETE", resultRecord.getStatus());

            // fee should be non-zero
            Assert.assertTrue(Double.parseDouble(resultRecord.getChargeAmount().substring(1)) > 0.0);

        }
//        results.forEach(System.out::println);
    }

    @Test // within the same day same bus same company ID and PAN, the OFF match to earlier closest record
    public void test2() throws IOException {

        final String test2 = "src/test/resources/data/test2";
//        ID, DateTimeUTC, TapType, StopId, CompanyId, BusID, PAN
//        1, 22-01-2018 13:00:00, ON, Stop1, Company1, Bus37, 5500005555555559
//        3, 22-01-2018 18:00:00, ON, Stop2, Company1, Bus37, 5500005555555559
//        4, 22-01-2018 15:00:00, OFF, Stop2, Company1, Bus37, 5500005555555559

        List<ResultRecord> results = workFlow.process(test2);

        Assert.assertEquals(2, results.size());

        // the first record should be complete
        ResultRecord completeRecord = results.get(0);
        Assert.assertEquals("COMPLETED", completeRecord.getStatus());

        // the second should be incomplete
        ResultRecord incompleteRecord = results.get(1);
        Assert.assertEquals("INCOMPLETE", incompleteRecord.getStatus());

//        results.forEach(System.out::println);

    }

}
