/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.Transaction;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface TransactionDao {

    Long addTransaction(Transaction tx);

    void delTransaction(Long txId);

    List findAllTransaction();

    Transaction findByTransactionId(Long txId);

    void updateTransaction(Transaction tx);
    
}
