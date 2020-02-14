package com.toprate.mct.rest;

import com.toprate.mct.bo.NewApp;
import com.toprate.mct.dao.NewAppDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.Collections;

public class NewServiceImpl implements NewService {

    @Autowired
    private NewAppDAO newAppDAO;

    @Override
    public Response getNew() {
        return Response.ok("GET NEW").build();
    }

    @Override
    public Response saveNew(NewApp obj) {
        try {
            newAppDAO.saveOrUpdate(obj);
            return Response.ok("SUCCESS").build();
        } catch (Exception e) {
            return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
        }
    }

    @Override
    public Response updateNew(NewApp obj) {
        try {
            String resultUpdate = newAppDAO.update(obj);
            return Response.ok(resultUpdate).build();
        } catch (Exception e) {
            return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
        }
    }

    @Override
    public Response get(long id) {
        try {
            NewApp newApp = newAppDAO.findById(id);
            if(newApp != null) {
                return Response.ok(newApp).build();
            }
            return Response.ok("Cannot find New").build();
        } catch (Exception e) {
            return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
        }
    }
}
