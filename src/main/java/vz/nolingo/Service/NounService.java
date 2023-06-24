package vz.nolingo.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vz.nolingo.Controller.BaseController;
import vz.nolingo.Controller.NounController;
import vz.nolingo.Entity.Noun;

@Service
public class NounService implements BaseController<Noun>  {
    
    @Autowired
    private NounController nounController;

    @Override
    public Noun save(Noun t) {
       return nounController.save(t);
    }

    @Override
    public List<Noun> findAll() {
        return nounController.findAll();
    }

    @Override
    public void deleteAll() {
        nounController.deleteAll();
    }

    @Override
    public void delete(Set<Noun> set) {
        nounController.delete(set);
    }

    

}
