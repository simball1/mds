package com.mysite.mds.director;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorForm {

	@NotEmpty(message="이름을 입력하세요")
	private String name;
}
