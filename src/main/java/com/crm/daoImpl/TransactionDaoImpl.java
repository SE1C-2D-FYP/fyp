/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.TransactionDao;
import com.crm.models.Transaction;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
public class TransactionDaoImpl implements TransactionDao {
    Session session = (Session) HibernateUtil.getSessionFactory();

    @Override
    public List findAllTransaction() {
        return null;
    }

    @Override
    public Transaction findByTransactionId(Long txId) {
        return null;
    }

    @Override
    public Long addTransaction(Transaction tx) {
        return null;
    }

    @Override
    public void updateTransaction(Transaction tx) {
    }

    @Override
    public void delTransaction(Long txId) {
    }
}
