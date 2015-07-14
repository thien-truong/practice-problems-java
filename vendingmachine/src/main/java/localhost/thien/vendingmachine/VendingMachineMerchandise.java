package localhost.thien.vendingmachine;

public class VendingMachineMerchandise {
    private String merchandiseCode;
    private String merchandiseName;
    private double retailPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendingMachineMerchandise that = (VendingMachineMerchandise) o;

        if (Double.compare(that.retailPrice, retailPrice) != 0) return false;
        if (merchandiseCode != null ? !merchandiseCode.equals(that.merchandiseCode) : that.merchandiseCode != null)
            return false;
        return !(merchandiseName != null ? !merchandiseName.equals(that.merchandiseName) : that.merchandiseName != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = merchandiseCode != null ? merchandiseCode.hashCode() : 0;
        result = 31 * result + (merchandiseName != null ? merchandiseName.hashCode() : 0);
        temp = Double.doubleToLongBits(retailPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getMerchandiseCode() {
        return merchandiseCode;
    }

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public VendingMachineMerchandise(String merchandiseCode) {
        this.merchandiseCode = merchandiseCode;
        switch (merchandiseCode) {
            case "A":
                this.merchandiseName = "Pepsi";
                this.retailPrice = 0.75;
                break;
            case "B":
                this.merchandiseName = "Diet Pepsi";
                this.retailPrice = 0.75;
                break;
            case "C":
                this.merchandiseName = "Red Bull";
                this.retailPrice = 2.50;
                break;
            case "D":
                this.merchandiseName = "Diet Red Bull";
                this.retailPrice = 2.50;
                break;
            default:
                break;
        }
    }


}
