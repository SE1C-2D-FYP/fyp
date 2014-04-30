/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crm.daoImpl;

import com.crm.models.Title;
import com.crm.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cliff
 */
public class TitleDaoImpl {
       Session session = null;

    public TitleDaoImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    public Title findAll(){
//        Language language =  (Language) session.get(Language.class, 1); 
                
		Query query = session.getNamedQuery("Title.findAll");

		List<?> list = query.list();

		if (!list.isEmpty()) {
			Title language = (Title) list.get(0);
                        
//			System.out.println(language);
                        return language;
		}                
        return null;
    }
}
