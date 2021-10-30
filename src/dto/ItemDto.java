package dto;

import java.util.Objects;

public class ItemDto {
    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;

    public ItemDto() {
    }

    public ItemDto(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand) {
        this.itemCode = itemCode;
        this.description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto item = (ItemDto) o;
        return Double.compare(item.unitPrice, unitPrice) == 0 && qtyOnHand == item.qtyOnHand && Objects.equals(itemCode, item.itemCode) && Objects.equals(description, item.description) && Objects.equals(packSize, item.packSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode);
    }
}
