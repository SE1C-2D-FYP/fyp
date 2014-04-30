/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.StockViewing;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface StockViewingDao {

    Long addStockViewing(StockViewing stockViewing);

    void delStockViewing(Long stockViewingId);

    List findAllStockViewing();

    StockViewing findByStockViewingId(Long stockViewingId);

    void updateStockViewing(StockViewing stockViewing);
    
}
