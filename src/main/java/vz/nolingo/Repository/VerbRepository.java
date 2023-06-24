package vz.nolingo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vz.nolingo.Entity.Verb;

public interface VerbRepository extends JpaRepository<Verb, Long> {}
    
    