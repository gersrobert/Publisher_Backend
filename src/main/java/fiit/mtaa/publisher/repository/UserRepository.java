package fiit.mtaa.publisher.repository;

import fiit.mtaa.publisher.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
	AppUser getFirstByUserName(String userName);
	// AppUser getFirstByUserNameAndPasswordHash(String userName, String passwordHash);
    AppUser getFirstBySubject(String subject);
}