package bo;

import bo.custom.Impl.CustomerBoImpl;
import bo.custom.Impl.ItemBoImpl;
import bo.custom.Impl.OrderBoImpl;

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