package views.Tm;

public class CartTm {
    private String code;
    private String description;
    private String packSize;
    private int qtyOnHand;
    private int qty;
    private double unitPrice;
    private double total;

    public CartTm() {
    }

    public CartTm(String code, String description, String packSize, int qtyOnHand, int qty, double unitPrice, double total) {
        this.code = code;
        this.description = description;
        this.packSize = packSize;
        this.qtyOnHand = qtyOnHand;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
