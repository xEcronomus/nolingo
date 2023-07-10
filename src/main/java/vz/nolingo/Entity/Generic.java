package vz.nolingo.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "generic")
public class Generic extends BaseWord {

    @Override
    public void build(List<String> params) {
        this.german = params.get(0); 
        this.english = params.get(1); 
    }
    
}
