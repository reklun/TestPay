package DataObject;

import java.time.LocalDateTime;

/**
 * Record with appropriate data type
 * TODO potential builder pattern
 */
public class TravelRecord {

    private int id;

    private LocalDateTime dateTimeUTC;

    private Tap tapType;

    private String stopId;

    private String companyId;

    private String busID;

    private String pan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(LocalDateTime dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public Tap getTapType() {
        return tapType;
    }

    public void setTapType(Tap tapType) {
        this.tapType = tapType;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
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
