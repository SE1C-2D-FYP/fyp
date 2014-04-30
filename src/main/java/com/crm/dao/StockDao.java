/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.Stock;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface StockDao {

    String addStock(Stock stock);

    void delStock(String stockId);

    List findAllStock();

    Stock findByStockId(String stockId);

    void updateStock(Stock stock);
    
}
