package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.AppUserDTO;

import fiit.mtaa.publisher.entity.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<AppUserDTO> getUserById(@RequestHeader(name = "Authorization", required = false) String accessToken,
											  @PathVariable String uuid) {
		try {
			var user = userService.getUser(UUID.fromString(uuid));
			return ResponseEntity.ok(user);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping(value = "")
	public ResponseEntity<AppUserDTO> getUser(@RequestHeader(name = "Authorization", required = true) String accessToken) {
		try {
			var user = userService.getUser(accessToken);
			return ResponseEntity.ok(user);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(404).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(500).build();
		}
	}

	@PutMapping(value = "/set_photo")
	public ResponseEntity<?> setPhoto(@RequestHeader(name = "Authorization", required = false) String accessToken,
								   @RequestBody String photo) {
		var user = userService.checkIfUserExists(accessToken);

		var content = photo.split(",")[1];
		content = content.substring(0, content.length() - 2);

		try {
			userService.updatePhoto(user, Base64.getDecoder().decode(content));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(500).build();
		}
	}
}
