package vz.nolingo.UI;

import java.util.Set;

import org.reflections.Reflections;

import vz.nolingo.Entity.BaseWord;

public class ReflectionUtil {
    
    public static Set<Class<? extends BaseWord>> classes;

    
    public static Set<Class<? extends BaseWord>> getClasses(){
        if(classes == null){
            Reflections reflections = new Reflections();    
            classes = reflections.getSubTypesOf(BaseWord.class);
        }
        return classes;
    }
}
