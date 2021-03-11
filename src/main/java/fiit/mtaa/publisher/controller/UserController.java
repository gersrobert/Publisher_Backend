package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.AppUserWithPasswordDTO;
import fiit.mtaa.publisher.dto.IdDTO;
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

	@PostMapping(value = "/set_photo")
	public ResponseEntity setPhoto(@RequestBody Byte[] photo) {
		throw new RuntimeException("Not yet implemented");
	}

	@PostMapping(value = "/register")
	public ResponseEntity<IdDTO> registerUser(@RequestBody AppUserWithPasswordDTO user) {
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
