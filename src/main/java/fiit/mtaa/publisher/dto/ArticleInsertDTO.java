package fiit.mtaa.publisher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
import java.util.UUID;

public class ArticleInsertDTO {
	private UUID author;
	private String title;
	private List<String> categories;
	private String content;

	public String getTitle() {
		return title;
	}

	@JsonSetter
	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getCategories() {
		return categories;
	}

	@JsonSetter
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getContent() {
		return content;
	}

	@JsonSetter
	public void setContent(String content) {
		this.content = content;
	}

	public UUID getAuthor() {
		return author;
	}

	public void setAuthor(UUID author) {
		this.author = author;
	}
}
