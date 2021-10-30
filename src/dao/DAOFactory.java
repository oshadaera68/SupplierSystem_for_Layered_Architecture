package dao;

import dao.Custom.Impl.CustomerDaoImpl;
import dao.Custom.Impl.ItemDaoImpl;
import dao.Custom.Impl.OrderDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDao getDao(DaoTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDaoImpl();

            case ITEM:
                return new ItemDaoImpl();

            case ORDER:
                return new OrderDaoImpl();
                
            default:
                return null;
        }
    }

    public enum DaoTypes {
        CUSTOMER, ITEM, ORDER
    }
}