package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.dto.PublisherDetailedDTO;
import fiit.mtaa.publisher.dto.PublisherLeadershipDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/publisher")
public class PublisherController extends AbstractController {

	Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@GetMapping("/top")
	public ResponseEntity<List<PublisherLeadershipDTO>> getTopPublishers() {
		throw new RuntimeException("Not yet implemented");

	}

	@GetMapping("/{publisherId}/detail")
	public ResponseEntity<PublisherDetailedDTO> getRowOfPublisher(@PathVariable UUID publisherId) {
		throw new RuntimeException("Not yet implemented");

	}
}
