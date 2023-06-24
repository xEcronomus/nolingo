package vz.nolingo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vz.nolingo.Entity.BaseWord;

public interface BaseWordRepository extends JpaRepository<BaseWord, Long> {
    
}