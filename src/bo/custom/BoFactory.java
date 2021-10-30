package bo.custom;

import bo.custom.impl.CustomerBoImpl;
import bo.custom.impl.ItemBoImpl;
import bo.custom.impl.OrderBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        return boFactory == null ? boFactory = new BoFactory() : boFactory;
    }

    public SuperBo getBo(BoTypes types) {
        switch (types) {
            case ITEM:
                return new ItemBoImpl();

            case CUSTOMER:
                return new CustomerBoImpl();

            case PURCHASEORDER:
                return new OrderBoImpl();

            default:
                return null;
        }
    }

    public enum BoTypes {
        ITEM, CUSTOMER, PURCHASEORDER
    }
}