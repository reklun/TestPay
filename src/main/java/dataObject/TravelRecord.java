package dataObject;

import java.time.LocalDateTime;

/**
 * Record with appropriate data type
 * TODO potential builder pattern
 */
public class TravelRecord {

    private int id;

    private LocalDateTime dateTimeUTC;

    private Tap tapType;

    private StopID stopId;

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

    public StopID getStopId() {
        return stopId;
    }

    public void setStopId(StopID stopId) {
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

    /**
     * return true if two records matches the companyId, BusId and PAN
     * also this record is TAP ON looking for a TAP OFF target
     *
     * @param TravelRecord
     * @return
     */
    public boolean isPairing(TravelRecord that) {
        if (this.tapType == Tap.OFF &&
                that.tapType == Tap.ON &&
                this.companyId.equals(that.companyId) &&
                this.busID.equals(that.busID) &&
                this.pan.equals(that.pan)
        ) {
            return true;
        }

        return false;
    }

//    /**
//     * Pairing conditions adding a time contraint on top
//     * @param TravelRecord
//     * @return
//     */
//    public boolean isTightPairing(TravelRecord that) {
//        if (isPairing(that)) {
//            if (that.dateTimeUTC.isBefore(this.dateTimeUTC.plusHours(Constant.EXPIRY))) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    /**
     * for generating a record id
     *
     * @return
     */
    public String generateKey() {
        return this.companyId + this.busID + this.pan;
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
