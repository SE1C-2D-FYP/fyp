/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.StockViewingDao;
import com.crm.models.StockViewing;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
public class StockViewingDaoImpl implements StockViewingDao {
    Session session = (Session) HibernateUtil.getSessionFactory();

    @Override
    public List findAllStockViewing() {
        return null;
    }

    @Override
    public StockViewing findByStockViewingId(Long stockViewingId) {
        return null;
    }

    @Override
    public Long addStockViewing(StockViewing stockViewing) {
        return null;
    }

    @Override
    public void updateStockViewing(StockViewing stockViewing) {
    }

    @Override
    public void delStockViewing(Long stockViewingId) {
    }
}
