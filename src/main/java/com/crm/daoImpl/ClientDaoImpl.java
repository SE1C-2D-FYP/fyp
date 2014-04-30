/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.dao.ClientDao;
import com.crm.models.Client;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
//
public class ClientDaoImpl implements ClientDao {

    Session session = (Session) HibernateUtil.getSessionFactory();

    @Override
    public List findAllClient() {
        return null;
    }

    @Override
    public Client findByClientId(Long clientId) {
        return null;
    }

    @Override
    public Long addClient(Client client) {
        return null;
    }

    @Override
    public void updateClient(Client client) {
    }

    @Override
    public void delClient(Long clientId) {
    }
}
