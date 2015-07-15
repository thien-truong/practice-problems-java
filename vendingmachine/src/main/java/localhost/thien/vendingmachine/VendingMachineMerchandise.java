package localhost.thien.vendingmachine;

public class VendingMachineMerchandise {

    private String merchandiseName;
    private double retailPrice;

    public String getMerchandiseName() {
        return merchandiseName;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public VendingMachineMerchandise(String merchandiseName, double retailPrice) {
        this.merchandiseName = merchandiseName;
        this.retailPrice = retailPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendingMachineMerchandise that = (VendingMachineMerchandise) o;

        return Double.compare(that.retailPrice, retailPrice) == 0 &&
               !(merchandiseName != null ? !merchandiseName.equals(that.merchandiseName) : that.merchandiseName != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = merchandiseName != null ? merchandiseName.hashCode() : 0;
        temp = Double.doubleToLongBits(retailPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
