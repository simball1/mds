package com.mysite.mds.director;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DirectorService {

	private final DirectorRepository directorRepository;
	
	public void create(String name) {
		Director director = new Director();
		director.setName(name);
		this.directorRepository.save(director);
	}
	
}
