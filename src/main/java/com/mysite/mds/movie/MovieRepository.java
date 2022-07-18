package com.mysite.mds.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Movie findByTitle(String title);
	Page<Movie> findAll(Pageable pageable);
}
