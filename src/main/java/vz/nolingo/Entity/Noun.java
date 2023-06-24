package vz.nolingo.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import vz.nolingo.Enum.NounPronoun;

@Entity
@Getter
@Table(name = "noun")
public class Noun extends BaseWord {

    public Noun(){
        super();
    }

    @Enumerated
    @Column
    public NounPronoun pronoun;

    @Column
    public String plural;

    @Override
    public void build(List<String> params) {
        this.pronoun = NounPronoun.valueOf(params.get(3)); 
        this.plural = params.get(2);
        this.english = params.get(1); 
        this.german = params.get(0); 
        
    }

    
    
}
