/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.dao;

import com.crm.models.Client;
import java.util.List;

/**
 *
 * @author Cliff
 */
public interface ClientDao {

    Long addClient(Client client);

    void delClient(Long clientId);

    List findAllClient();

    Client findByClientId(Long clientId);

    void updateClient(Client client);
    
}
