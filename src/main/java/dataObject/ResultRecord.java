package dataObject;

/**
 * This is a CSV POJO Object, which the variable name needs to match the header
 * <p>
 * Example
 * Started, Finished, DurationSecs, FromStopId, ToStopId, ChargeAmount, CompanyId, BusID, PAN, Status
 * 22-01-2018 13:00:00, 22-01-2018 13:05:00, 900, Stop1, Stop2, $3.25, Company1, B37, 5500005555555559, COMPLETED
 */

public class ResultRecord {

    // can use annotation name and reordering fields by extending existing csv writer class
    // it is just too complicated for a line of header
    public static final String[] COLUMNS = {
            "Started", "Finished", "DurationSecs", "FromStopId",
            "ToStopId", "ChargeAmount", "CompanyId", "BusID", "PAN", "Status"
    };

    // for building the header line with predefined fields
    public static String HEADER;

    public static String getCSVHeader() {
        if (HEADER == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < COLUMNS.length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(COLUMNS[i]);
            }

            HEADER = sb.toString();
        }

        return HEADER;
    }

    private String Started;

    private String Finished;

    private String DurationSecs;

    private String FromStopId;

    private String ToStopId;

    private String ChargeAmount;

    private String CompanyId;

    private String BusID;

    private String PAN;

    private String Status;

    public String getStarted() {
        return Started;
    }

    public void setStarted(String started) {
        Started = started;
    }

    public String getFinished() {
        return Finished;
    }

    public void setFinished(String finished) {
        Finished = finished;
    }

    public String getDurationSecs() {
        return DurationSecs;
    }

    public void setDurationSecs(String durationSecs) {
        DurationSecs = durationSecs;
    }

    public String getFromStopId() {
        return FromStopId;
    }

    public void setFromStopId(String fromStopId) {
        FromStopId = fromStopId;
    }

    public String getToStopId() {
        return ToStopId;
    }

    public void setToStopId(String toStopId) {
        ToStopId = toStopId;
    }

    public String getChargeAmount() {
        return ChargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        ChargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getBusID() {
        return BusID;
    }

    public void setBusID(String busID) {
        BusID = busID;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "ResultRecord{" +
                "Started='" + Started + '\'' +
                ", Finished='" + Finished + '\'' +
                ", DurationSecs='" + DurationSecs + '\'' +
                ", FromStopId='" + FromStopId + '\'' +
                ", ToStopId='" + ToStopId + '\'' +
                ", ChargeAmount='" + ChargeAmount + '\'' +
                ", CompanyId='" + CompanyId + '\'' +
                ", BusID='" + BusID + '\'' +
                ", PAN='" + PAN + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
