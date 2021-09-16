package madzi.apps.tagit.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import madzi.apps.tagit.domain.DefaultResource;
import madzi.apps.tagit.domain.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Resource Service")
public class ResourceServiceTest {

    @Test
    @DisplayName("should be able to create self")
    public void testCreation() {
        final ResourceService resourceService = new InMemoryResourceService();
        assertNotNull(resourceService);
    }

    @Test
    @DisplayName("should be empty after creation")
    public void testEmptyService() {
        final ResourceService resourceService = new InMemoryResourceService();
        final AtomicLong counter = new AtomicLong();
        resourceService.findAll().forEach(item -> counter.getAndIncrement());
        assertEquals(0L, counter.get());
    }

    @Test
    @DisplayName("should store and find one resource")
    public void testWorkWithResource() {
        final Resource resource = DefaultResource.create("http://localhost", Map.of("info", "local hosting"));
        final ResourceService resourceService = new InMemoryResourceService();
        resourceService.add(resource);
        Optional<Resource> optResource = resourceService.find(resource.location());
        Assertions.assertTrue(optResource.isPresent());
    }
}
