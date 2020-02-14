package com.toprate.mct.dao;

import com.toprate.mct.bo.NewApp;
import com.viettel.service.base.dao.BaseFWDAOImpl;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("newAppDAO")
public class NewAppDAO extends BaseFWDAOImpl<NewApp, Long> {

    public NewAppDAO() {
        this.model = new NewApp();
    }

    @Transactional
    public void saveOrUpdate(NewApp NewApp) {
        getSession().save(NewApp);
    }

    @Transactional
    public String update(NewApp obj) {
        try {
            this.getSession().update(obj);
            return "SUCCESS";
        } catch (HibernateException var3) {
            log.error(var3.getMessage(), var3);
            return var3.getMessage();
        }
    }

    @Transactional
    public NewApp findById(Long id) {
        try {
            NewApp newApp = get(NewApp.class, id);
            return newApp;
        } catch (HibernateException var3) {
            log.error(var3.getMessage(), var3);
            return null;
        }
    }
}
