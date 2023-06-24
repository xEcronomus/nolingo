package vz.nolingo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public abstract class BaseWord implements Comparable<BaseWord> {

    public BaseWord(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String english;
    
    @Column
    public String german;

    @Column
    public Long score = 0l; 

    public int compareTo(BaseWord other){  
        if(this.score==other.score)
            return 0;  
        else if(this.score>other.score)  
            return 1;  
        else  
            return -1;  
    }

    public abstract void build(List<String> params);
    
}
