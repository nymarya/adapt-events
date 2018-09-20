package br.com.adapt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adapt.model.Freeblock;;

@Repository
public interface FreeBlockRepository extends JpaRepository<Freeblock, Integer> {
	
}