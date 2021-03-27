package fiit.mtaa.publisher.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserinfoDTO {
    protected String sub;
	protected String givenName;
	protected String familyName;
	protected String nickname;
	protected String name;
	protected String picture;
	protected String locale;
	protected String updatedAt;


    public String getSub() {
        return this.sub;
    }

	@JsonSetter
    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getGivenName() {
        return this.givenName;
    }

	@JsonSetter
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

	@JsonSetter
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getNickname() {
        return this.nickname;
    }

	@JsonSetter
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return this.name;
    }

	@JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return this.picture;
    }

	@JsonSetter
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLocale() {
        return this.locale;
    }

	@JsonSetter
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

	@JsonSetter
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
