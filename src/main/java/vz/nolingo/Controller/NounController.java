package vz.nolingo.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vz.nolingo.Entity.Noun;
import vz.nolingo.Repository.NounRepository;

@RestController
public class NounController implements BaseController<Noun> {
    
    @Autowired
    private NounRepository repository;

    @Override
    public Noun save(Noun noun){
        return repository.save(noun);
    }

    @Override
    public List<Noun> findAll(){
        return repository.findAll();
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

    @Override
    public void delete(Set<Noun> set) {
        repository.deleteAll(set);
    }
}

