package vz.nolingo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "verb")
public class Verb extends BaseWord {

    public Verb(){
        super();
    }

    @Column
    public String sie;
    @Column
    public String ihr;
    @Column
    public String wir;
    @Column
    public String er_sie_es;
    @Column
    public String du;
    @Column
    public String ich;
    

    @Override
    public void build(List<String> params) {
        this.german = params.get(0); 
        this.english = params.get(1); 
        this.ich = params.get(2); 
        this.du = params.get(3); 
        this.er_sie_es = params.get(4); 
        this.wir = params.get(5); 
        this.ihr = params.get(6); 
        this.sie = params.get(7); 
        
    }

    
    
}
