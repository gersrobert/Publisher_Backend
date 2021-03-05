package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.dto.CollectionDTO;
import fiit.mtaa.publisher.dto.CollectionInsertDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/collection")
public class CollectionController extends AbstractController {

	Logger logger = LoggerFactory.getLogger(CollectionController.class);

	@GetMapping
	public ResponseEntity<Collection<CollectionDTO>> getCollectionList() {
		throw new RuntimeException("Not yet implemented");
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<CollectionDTO> getCollection(@PathVariable String id, @RequestHeader("Auth-Token") String userId) {
		throw new RuntimeException("Not yet implemented");
	}

	@GetMapping(path = "/user/{userId}")
	public ResponseEntity<Collection<CollectionDTO>> getCollectionsForUser(@PathVariable String userId) {
		throw new RuntimeException("Not yet implemented");

	}

	@PostMapping
	public ResponseEntity<?> insertCollection(@RequestBody CollectionInsertDTO collectionInsertDTO) {
		throw new RuntimeException("Not yet implemented");

	}

	@PutMapping(path = "/{collectionId}/assign/{articleId}")
	public ResponseEntity<?> assignArticleToCollection(@PathVariable String collectionId, @PathVariable String articleId) {
		throw new RuntimeException("Not yet implemented");

	}

	@PutMapping
	public ResponseEntity<?> assignArticleToCollection(@RequestBody CollectionDTO collectionDTO) {
		throw new RuntimeException("Not yet implemented");

	}
}
