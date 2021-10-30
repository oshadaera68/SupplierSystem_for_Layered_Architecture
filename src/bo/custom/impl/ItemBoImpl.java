package bo.custom.impl;

import bo.custom.ItemBo;
import dao.Custom.Impl.ItemDaoImpl;
import dao.Custom.ItemDao;
import dao.DAOFactory;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    private final ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDao(DAOFactory.DaoTypes.ITEM);

    @Override
    public boolean addItem(Item item) throws SQLException, ClassNotFoundException {
        return itemDao.add(item);
    }

    @Override
    public Item searchById(String itemId) throws SQLException, ClassNotFoundException {
        return itemDao.searchById(itemId);
    }

    @Override
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDao.delete(itemCode);
    }

    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDao.getAll();
    }

    @Override
     public boolean updateItem(Item item) throws SQLException, ClassNotFoundException {
        return itemDao.update(item);
    }
}
