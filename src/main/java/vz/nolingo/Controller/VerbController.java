package vz.nolingo.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vz.nolingo.Entity.Verb;
import vz.nolingo.Repository.VerbRepository;

@RestController
public class VerbController implements BaseController<Verb> {
    
    @Autowired
    private VerbRepository repository;


    @Override
    public Verb save(Verb verb){
        return repository.save(verb);
    }

    @Override
    public List<Verb> findAll(){
        return repository.findAll();
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

    @Override
    public void delete(Set<Verb> set) {
        repository.deleteAll(set);
    }
}
