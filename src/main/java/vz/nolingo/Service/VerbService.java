package vz.nolingo.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vz.nolingo.Controller.BaseController;
import vz.nolingo.Controller.VerbController;
import vz.nolingo.Entity.Verb;

@Service
public class VerbService implements BaseController<Verb> {
     
    @Autowired
    private VerbController verbController;

    @Override
    public Verb save(Verb t) {
       return verbController.save(t);
    }

    @Override
    public List<Verb> findAll() {
        return verbController.findAll();
    }

    @Override
    public void deleteAll() {
        verbController.deleteAll();
    }

    @Override
    public void delete(Set<Verb> set) {
        verbController.delete(set);
    }

    
}
