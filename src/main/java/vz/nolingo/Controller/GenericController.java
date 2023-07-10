package vz.nolingo.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vz.nolingo.Entity.Generic;
import vz.nolingo.Repository.GenericRepository;

@RestController
public class GenericController implements BaseController<Generic> {
    
    @Autowired
    private GenericRepository repository;


    @Override
    public Generic save(Generic verb){
        return repository.save(verb);
    }

    @Override
    public List<Generic> findAll(){
        return repository.findAll();
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

    @Override
    public void delete(Set<Generic> set) {
        repository.deleteAll(set);
    }
}