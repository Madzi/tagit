package madzi.apps.tagit.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import madzi.apps.tagit.domain.Resource;

public class InMemoryResourceService implements ResourceService {

    private final Map<URI, Resource> resources;

    public InMemoryResourceService() {
        this.resources = new HashMap<>();
    }

    @Override
    public Optional<Resource> find(URI location) {
        if (resources.containsKey(location)) {
            return Optional.of(resources.get(location));
        }

        return Optional.empty();
    }

    @Override
    public void add(Resource resource) {
        resources.put(resource.location(), resource);
    }

    @Override
    public Iterable<Resource> findAll() {
        return resources.values();
    }

    @Override
    public void addAll(Iterable<Resource> resources) {
        resources.forEach(this::add);
    }
}
