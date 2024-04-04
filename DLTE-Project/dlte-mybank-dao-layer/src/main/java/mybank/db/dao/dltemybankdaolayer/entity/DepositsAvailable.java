package mybank.db.dao.dltemybankdaolayer.entity;


public class DepositsAvailable {
   private long depositId;
   private String depositName, depositType,depositDescription;
   private double depositRoi;

    public DepositsAvailable() {
    }

    public DepositsAvailable(long depositId, String depositName, String depositType, String depositDescription, double depositRoi) {
        this.depositId = depositId;
        this.depositName = depositName;
        this.depositType = depositType;
        this.depositDescription = depositDescription;
        this.depositRoi = depositRoi;
    }

    @Override
    public String toString() {
        return "DepositsAvailable{" +
                "depositId=" + depositId +
                ", depositName='" + depositName + '\'' +
                ", depositType='" + depositType + '\'' +
                ", depositDescription='" + depositDescription + '\'' +
                ", depositRoi=" + depositRoi +
                '}';
    }

    public long getDeposit_id() {
        return depositId;
    }

    public void setDeposit_id(long depositId) {
        this.depositId = depositId;
    }

    public String getDeposit_name() {
        return depositName;
    }

    public void setDeposit_name(String depositName) {
        this.depositName = depositName;
    }

    public String getDeposit_type() {
        return depositType;
    }

    public void setDeposit_type(String depositType) {
        this.depositType = depositType;
    }

    public String getDeposit_description() {
        return depositDescription;
    }

    public void setDeposit_description(String depositDescription) {
        this.depositDescription = depositDescription;
    }

    public double getDeposit_roi() {
        return depositRoi;
    }

    public void setDeposit_roi(double depositRoi) {
        this.depositRoi = depositRoi;
    }
}
