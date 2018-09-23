package br.com.adapt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.adapt.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
	@Query("select u from Tag u where u.id = ?1")
	Tag findById(int id);
}
