package fiit.mtaa.publisher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class ArticleInsertDTO {
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
}
