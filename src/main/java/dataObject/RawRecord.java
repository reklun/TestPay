package dataObject;

import com.opencsv.bean.CsvBindByPosition;

/**
 * For storing the primitive data parsed from the CSV file
 */
public class RawRecord {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String dateTimeUTC;

    @CsvBindByPosition(position = 2)
    private String tapType;

    @CsvBindByPosition(position = 3)
    private String stopId;

    @CsvBindByPosition(position = 4)
    private String companyId;

    @CsvBindByPosition(position = 5)
    private String busID;

    @CsvBindByPosition(position = 6)
    private String pan;

    public String getId() {
        return id;
    }

    public String getDateTimeUTC() {
        return dateTimeUTC;
    }

    public String getTapType() {
        return tapType;
    }

    public String getStopId() {
        return stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBusID() {
        return busID;
    }

    public String getPan() {
        return pan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(", ");
        sb.append(dateTimeUTC);
        sb.append(", ");
        sb.append(tapType);
        sb.append(", ");
        sb.append(stopId);
        sb.append(", ");
        sb.append(companyId);
        sb.append(", ");
        sb.append(busID);
        sb.append(", ");
        sb.append(pan);
        return sb.toString();
    }
}

