package com.mysite.mds.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Movie findByTitle(String title);
}
