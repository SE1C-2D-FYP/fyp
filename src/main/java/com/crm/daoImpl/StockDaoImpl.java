/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.StockDao;
import com.crm.models.Stock;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
public class StockDaoImpl implements StockDao {
    Session session = (Session) HibernateUtil.getSessionFactory();

    @Override
    public List findAllStock() {
        return null;
    }

    @Override
    public Stock findByStockId(String stockId) {
        return null;
    }

    @Override
    public String addStock(Stock stock) {
        return null;
    }

    @Override
    public void updateStock(Stock stock) {
    }

    @Override
    public void delStock(String stockId) {
    }
}
