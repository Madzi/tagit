package madzi.apps.tagit.service;

import java.net.URI;
import java.util.Optional;

import madzi.apps.tagit.domain.Resource;

/**
 * Service for resources.
 */
public interface ResourceService {

    Optional<Resource> find(URI location);

    void add(Resource resource);

    boolean remove(Resource resource);

    /**
     * Returns all available resources.
     *
     * @return sequence of resources
     */
    Iterable<Resource> findAll();

    void addAll(Iterable<Resource> resources);
}
