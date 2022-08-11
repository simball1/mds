package com.mysite.mds.movie;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieForm {

	@NotEmpty(message = "영화제목을 입력하세요")
	private String title;
	
	@NotEmpty(message = "감독이름을 입력하세요")
	private String directorName;
	
	private String actorName;
	
	private String content;

}
