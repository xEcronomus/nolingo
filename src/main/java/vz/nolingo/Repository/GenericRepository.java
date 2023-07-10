package vz.nolingo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vz.nolingo.Entity.Generic;

public interface GenericRepository extends JpaRepository<Generic, Long> {
}
