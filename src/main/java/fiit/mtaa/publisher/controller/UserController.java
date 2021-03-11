package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.AppUserDetailedDTO;
import fiit.mtaa.publisher.dto.AppUserWithPasswordDTO;
import fiit.mtaa.publisher.dto.LoginRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController extends AbstractController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/login", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<UUID> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		throw new RuntimeException("Not yet implemented");
	}

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<AppUserDTO> getUser(@PathVariable String uuid) {
		throw new RuntimeException("Not yet implemented");
	}

	@GetMapping(value = "/{uuid}/detailed")
	public ResponseEntity<AppUserDetailedDTO> getUserDetailed(@PathVariable String uuid) {
		throw new RuntimeException("Not yet implemented");
	}

	@PostMapping(value = "/register")
	public ResponseEntity registerUser(@RequestBody AppUserWithPasswordDTO user) {
		// boolean response = true;

		// System.out.println(user.getPasswordHash());
		
		// // try {
		// // 	response = userService.registerAppUser(user);
		// // } catch (Exception e) {
		// // 	logger.error("Error registering user", e);
		// // 	throw new InternalServerException(e);
		// // }

		// if (response) {
		// 	return new ResponseEntity(HttpStatus.CREATED);
		// } else {
		// 	return new ResponseEntity(HttpStatus.FORBIDDEN);
		// }
		throw new RuntimeException("Not yet implemented");
	}

	@PostMapping(value = "/set_photo")
	public ResponseEntity setPhoto(@RequestBody Byte[] photo) {
		throw new RuntimeException("Not yet implemented");
	}

	@GetMapping(value = "/{userId}/actions/{articleId}")
	public ResponseEntity<Collection<String>> getActions(@PathVariable String userId, @PathVariable String articleId) {
		throw new RuntimeException("Not yet implemented");

	}

	// @GetMapping(value = "/test")
	// public ResponseEntity<AppUserDTO> getTest() {
	// 	AppUserDTO response = new AppUserDTO();
	// 	response.setFirstName("firstName");
	// 	response.setLastName("lastName");
	// 	response.setUserName("userName");
	// 	response.setId("id");

	// 	return ResponseEntity.of(Optional.of(response));
	// }
}
