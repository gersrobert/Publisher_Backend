package fiit.mtaa.publisher.dto;

import java.time.LocalDateTime;

public class CommentDTO extends AbstractDTO {

	protected String content;
	protected AppUserDTO author;
	protected LocalDateTime createdAt;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AppUserDTO getAuthor() {
		return author;
	}

	public void setAuthor(AppUserDTO author) {
		this.author = author;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
