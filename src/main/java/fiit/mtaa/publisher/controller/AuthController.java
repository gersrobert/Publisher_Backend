package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.dto.LoginRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController extends AbstractController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/validate", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Jwt> validate(@RequestBody LoginRequestDTO loginRequestDTO) {
		throw new RuntimeException("Not yet implemented");
	}

    @PostMapping(value = "/renew", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Jwt> validate(@RequestBody Jwt jwt) {
		throw new RuntimeException("Not yet implemented");
	}
}
