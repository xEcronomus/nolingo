package vz.nolingo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import vz.nolingo.Controller.BaseController;
import vz.nolingo.Controller.GenericController;
import vz.nolingo.Controller.NounController;
import vz.nolingo.Controller.VerbController;
import vz.nolingo.Entity.BaseWord;
import vz.nolingo.Entity.Generic;
import vz.nolingo.Entity.Noun;
import vz.nolingo.Entity.Verb;

@Service
@Qualifier("BaseWordService")
public class BaseWordService {
    
    @Autowired
    private NounController nounController;
    @Autowired
    private VerbController verbController;
    @Autowired
    private GenericController genericController;


    @SuppressWarnings("all")
    @SneakyThrows
    public BaseController getController(Class<? extends BaseWord> clazz){
        BaseWord a = clazz.newInstance();
        if(a instanceof Noun){
            return nounController;
        }
        else if(a instanceof Verb){
            return verbController;
        }
        else if(a instanceof Generic){
            return genericController;
        }
        throw new IllegalArgumentException(":)");
    }
}
