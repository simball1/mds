package com.mysite.mds.director;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer>{

	Optional<Director> findByName(String name);
}
