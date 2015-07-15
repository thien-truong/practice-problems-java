package localhost.thien.vendingmachine;

public class VendingMachineMerchandiseStock {
    private VendingMachineMerchandise merchandise;
    private int quantity;

    public VendingMachineMerchandiseStock(VendingMachineMerchandise merchandise, int quantity) {
        this.merchandise = merchandise;
        this.quantity = quantity;
    }

    public VendingMachineMerchandise getMerchandise() {
        return merchandise;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendingMachineMerchandiseStock that = (VendingMachineMerchandiseStock) o;

        return quantity == that.quantity &&
               !(merchandise != null ? !merchandise.equals(that.merchandise) : that.merchandise != null);

    }

    @Override
    public int hashCode() {
        int result = merchandise != null ? merchandise.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
