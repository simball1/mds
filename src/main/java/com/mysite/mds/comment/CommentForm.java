package com.mysite.mds.comment;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {

	@NotEmpty(message = "별점은 필수항목입니다.")
	private String stars;
	
	private String content;
}
