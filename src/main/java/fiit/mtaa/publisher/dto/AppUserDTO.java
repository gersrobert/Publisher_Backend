package fiit.mtaa.publisher.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AppUserDTO extends AbstractDTO {

	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String photo;
	protected List<ArticleSimpleDTO> articles;

	public String getFirstName() {
		return firstName;
	}

	@JsonSetter
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@JsonSetter
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	@JsonSetter
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	@JsonSetter
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<ArticleSimpleDTO> getArticles() {
		return articles;
	}

	@JsonSetter
	public void setArticles(List<ArticleSimpleDTO> articles) {
		this.articles = articles;
	}
}
