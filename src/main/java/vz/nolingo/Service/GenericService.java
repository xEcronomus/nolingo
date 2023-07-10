package vz.nolingo.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vz.nolingo.Controller.BaseController;
import vz.nolingo.Controller.GenericController;
import vz.nolingo.Entity.Generic;

@Service
public class GenericService implements BaseController<Generic> {

     @Autowired
    private GenericController genericController;

    @Override
    public Generic save(Generic t) {
       return genericController.save(t);
    }

    @Override
    public List<Generic> findAll() {
        return genericController.findAll();
    }

    @Override
    public void deleteAll() {
        genericController.deleteAll();
    }

    @Override
    public void delete(Set<Generic> set) {
        genericController.delete(set);
    }

    

    
}
