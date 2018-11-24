package com.ifosup.coworking.api.resource;

import com.codahale.metrics.annotation.Timed;
import com.ifosup.coworking.api.util.HeaderUtil;
import com.ifosup.coworking.api.util.ResponseUtil;
import com.ifosup.coworking.domain.Space;
import com.ifosup.coworking.repository.SpaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Space.
 */
@RestController
@RequestMapping("/api")
public class SpaceResource {

    private static final String ENTITY_NAME = "space";
    private final Logger log = LoggerFactory.getLogger(SpaceResource.class);
    private final SpaceRepository spaceRepository;

    public SpaceResource(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    /**
     * POST  /spaces : Create a new space.
     *
     * @param space the space to create
     * @return the ResponseEntity with status 201 (Created) and with body the new space, or with status 400 (Bad Request) if the space has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/spaces")
    @Timed
    public ResponseEntity<Space> createSpace(@Valid @RequestBody Space space) throws URISyntaxException {
        log.debug("REST request to save Space : {}", space);
        if (space.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new space cannot already have an ID")).body(null);
        }
        Space result = spaceRepository.save(space);
        return ResponseEntity.created(new URI("/api/spaces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /spaces : Updates an existing space.
     *
     * @param space the space to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated space,
     * or with status 400 (Bad Request) if the space is not valid,
     * or with status 500 (Internal Server Error) if the space couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/spaces")
    @Timed
    public ResponseEntity<Space> updateSpace(@Valid @RequestBody Space space) throws URISyntaxException {
        log.debug("REST request to update Space : {}", space);
        if (space.getId() == null) {
            return createSpace(space);
        }
        Space result = spaceRepository.save(space);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, space.getId().toString()))
            .body(result);
    }

    /**
     * GET  /spaces : get all the spaces.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of spaces in body
     */
    @GetMapping("/spaces")
    @Timed
    public List<Space> getAllSpaces() {
        log.debug("REST request to get all Spaces");
        return spaceRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /spaces/:id : get the "id" space.
     *
     * @param id the id of the space to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the space, or with status 404 (Not Found)
     */
    @GetMapping("/spaces/{id}")
    @Timed
    public ResponseEntity<Space> getSpace(@PathVariable Long id) {
        log.debug("REST request to get Space : {}", id);
        Space space = spaceRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(space));
    }

    /**
     * DELETE  /spaces/:id : delete the "id" space.
     *
     * @param id the id of the space to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/spaces/{id}")
    @Timed
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        log.debug("REST request to delete Space : {}", id);
        spaceRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
