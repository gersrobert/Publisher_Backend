package fiit.mtaa.publisher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class CommentInsertDTO {
	private String content;

	public String getContent() {
		return content;
	}

	@JsonSetter
	public void setContent(String content) {
		this.content = content;
	}
}
