package fiit.mtaa.publisher.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Where(clause = "state='ACTIVE'")
public abstract class AbstractEntity {

	public static final String STATE_ACTIVE = "ACTIVE";
	public static final String STATE_DELETED = "DELETED";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	@Type(type = "uuid-char")
	protected UUID id;

	protected LocalDateTime createdAt;

	@UpdateTimestamp
	protected LocalDateTime updatedAt;

	protected String state = STATE_ACTIVE;

	public UUID getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractEntity)) return false;
		AbstractEntity that = (AbstractEntity) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@PrePersist
	void onCreate() {
		if (this.createdAt == null) {
			this.setCreatedAt(LocalDateTime.now());
		}
	}
}
