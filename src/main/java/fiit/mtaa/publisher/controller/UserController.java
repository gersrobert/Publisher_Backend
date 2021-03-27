package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.AppUserDTO;
import fiit.mtaa.publisher.dto.LoginRequestDTO;
import fiit.mtaa.publisher.exception.InternalServerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value = "/{uuid}")
	public ResponseEntity<AppUserDTO> getUser(@PathVariable String uuid) {
		throw new RuntimeException("Not yet implemented");
	}

	@PostMapping(value = "/set_photo")
	public ResponseEntity setPhoto(@RequestBody Byte[] photo) {
		throw new RuntimeException("Not yet implemented");
	}

	@GetMapping(value = "/test1")
	public ResponseEntity getTest1() {
		System.out.println("test1 successful");
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/test2")
	public ResponseEntity getTest2() {
		System.out.println("test2 successful");
		return new ResponseEntity(HttpStatus.OK);
	}
}
