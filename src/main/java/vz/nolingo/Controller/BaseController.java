package vz.nolingo.Controller;

import java.util.List;
import java.util.Set;

public interface BaseController<T> {
    
    public T save(T t);
    public List<T> findAll();
    public void deleteAll();
    public void delete(Set<T> set);

}
