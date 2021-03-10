package fiit.mtaa.publisher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
import java.util.UUID;

public class ArticleInsertDTO {
	private UUID id;
	private UUID authors;
	private String title;
	private List<String> categories;
	private String content;

	public UUID getId() {
		return id;
	}

	@JsonSetter
	public void setId(UUID id) {
		this.id = id;
	}

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

	public UUID getAuthors() {
		return authors;
	}

	public void setAuthors(UUID authors) {
		this.authors = authors;
	}
}
