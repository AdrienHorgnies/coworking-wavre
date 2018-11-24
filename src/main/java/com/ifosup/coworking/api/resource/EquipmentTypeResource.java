package com.ifosup.coworking.api.resource;

import com.ifosup.coworking.api.util.HeaderUtil;
import com.ifosup.coworking.api.util.ResponseUtil;
import com.ifosup.coworking.domain.EquipmentType;
import com.ifosup.coworking.repository.EquipmentTypeRepository;
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
 * REST controller for managing EquipmentType.
 */
@RestController
@RequestMapping("/api")
public class EquipmentTypeResource {

    private static final String ENTITY_NAME = "equipmentType";
    private final Logger log = LoggerFactory.getLogger(EquipmentTypeResource.class);
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentTypeResource(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    /**
     * POST  /equipment-types : Create a new equipmentType.
     *
     * @param equipmentType the equipmentType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new equipmentType, or with status 400 (Bad Request) if the equipmentType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/equipment-types")
    public ResponseEntity<EquipmentType> createEquipmentType(@Valid @RequestBody EquipmentType equipmentType) throws URISyntaxException {
        log.debug("REST request to save EquipmentType : {}", equipmentType);
        if (equipmentType.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new equipmentType cannot already have an ID")).body(null);
        }
        EquipmentType result = equipmentTypeRepository.save(equipmentType);
        return ResponseEntity.created(new URI("/api/equipment-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /equipment-types : Updates an existing equipmentType.
     *
     * @param equipmentType the equipmentType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated equipmentType,
     * or with status 400 (Bad Request) if the equipmentType is not valid,
     * or with status 500 (Internal Server Error) if the equipmentType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/equipment-types")
    public ResponseEntity<EquipmentType> updateEquipmentType(@Valid @RequestBody EquipmentType equipmentType) throws URISyntaxException {
        log.debug("REST request to update EquipmentType : {}", equipmentType);
        if (equipmentType.getId() == null) {
            return createEquipmentType(equipmentType);
        }
        EquipmentType result = equipmentTypeRepository.save(equipmentType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, equipmentType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /equipment-types : get all the equipmentTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of equipmentTypes in body
     */
    @GetMapping("/equipment-types")
    public List<EquipmentType> getAllEquipmentTypes() {
        log.debug("REST request to get all EquipmentTypes");
        return equipmentTypeRepository.findAll();
    }

    /**
     * GET  /equipment-types/:id : get the "id" equipmentType.
     *
     * @param id the id of the equipmentType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the equipmentType, or with status 404 (Not Found)
     */
    @GetMapping("/equipment-types/{id}")
    public ResponseEntity<EquipmentType> getEquipmentType(@PathVariable Long id) {
        log.debug("REST request to get EquipmentType : {}", id);
        EquipmentType equipmentType = equipmentTypeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(equipmentType));
    }

    /**
     * DELETE  /equipment-types/:id : delete the "id" equipmentType.
     *
     * @param id the id of the equipmentType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/equipment-types/{id}")
    public ResponseEntity<Void> deleteEquipmentType(@PathVariable Long id) {
        log.debug("REST request to delete EquipmentType : {}", id);
        equipmentTypeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
