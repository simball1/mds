package com.mysite.mds.actor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

	Optional<Actor> findByName(String name);
}
