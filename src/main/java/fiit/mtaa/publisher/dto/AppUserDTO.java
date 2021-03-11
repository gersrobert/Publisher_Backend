package fiit.mtaa.publisher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class AppUserDTO extends AbstractDTO {

	protected String firstName;
	protected String lastName;
	protected String userName;
	protected Byte[] photo;

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

	public Byte[] getPhoto() {
		return photo;
	}

	@JsonSetter
	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}
}
