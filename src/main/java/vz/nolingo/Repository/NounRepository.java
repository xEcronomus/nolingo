package vz.nolingo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vz.nolingo.Entity.Noun;

public interface NounRepository extends JpaRepository<Noun, Long> {
    
}
