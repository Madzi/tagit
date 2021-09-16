package madzi.apps.tagit.domain;

import java.net.URI;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Resource")
public class ResourceTest {

    @Test
    @DisplayName("should be able be created only with location")
    public void testCreateWithLocationOnly() {
        final var location = "htts://localhost";
        final var resource = DefaultResource.create(location);
        assertNotNull(resource);
        assertEquals(URI.create(location), resource.location());
        assertTrue(resource.tags().isEmpty());
    }

    @Test
    @DisplayName("should be able be created with location and tags")
    public void testCreateWithLocationAndTags() {
        final var location = "htts://localhost";
        final var resource = DefaultResource.create(location, Map.of("tag", "value"));
        assertNotNull(resource);
        assertEquals(URI.create(location), resource.location());
        assertEquals(1, resource.tags().size());
    }

    @Test
    @DisplayName("should fail when location not provided")
    public void testCreateFailWithoutLocation() {
        assertThrows(NullPointerException.class, () -> {
            DefaultResource.create((String) null);
        });
    }

    @Test
    @DisplayName("should be able provide value associated with tag")
    public void testGetTag() {
        final var location = "https://localhost";
        final var tagName = "tag-name";
        final var tagValue = "tag-value";
        final var resource = DefaultResource.create(location, Map.of(tagName, tagValue));
        assertEquals(tagValue, resource.getTag(tagName));
    }

    @Test
    @DisplayName("should be able accept new tag")
    public void testAddTag() {
        final var location = "htts://localhost";
        final var resource = DefaultResource.create(location);
        assertTrue(resource.tags().isEmpty());

        resource.addTag("demo", "demo description");
        assertFalse(resource.tags().isEmpty());
    }

    @Test
    @DisplayName("should be able to test if tag is present")
    public void testHasTag() {
        final var location = "https://localhost";
        final var tagName = "tag-name";
        final var tagValue = "tag-value";
        final var resource = DefaultResource.create(location, Map.of(tagName, tagValue));
        assertTrue(resource.hasTag(tagName));
    }

    @Test
    @DisplayName("should provide possibility to remove tag")
    public void testRemoveTag() {
        final var location = "https://localhost";
        final var tagName = "tag-name";
        final var tagValue = "tag-value";
        final var resource = DefaultResource.create(location, Map.of(tagName, tagValue));
        assertTrue(resource.hasTag(tagName));

        resource.removeTag(tagName);
        assertFalse(resource.hasTag(tagName));
        assertTrue(resource.tags().isEmpty());
    }

    @Test
    @DisplayName("should provide all information for debuging")
    public void testToString() {
        final var location = "https://localhost";
        final var tagName = "tag-name";
        final var tagValue = "tag-value";
        final var resource = DefaultResource.create(location, Map.of(tagName, tagValue));
        final var debug = resource.toString();

        assertTrue(debug.contains("location"));
        assertTrue(debug.contains(location));
        assertTrue(debug.contains(tagName));
        assertTrue(debug.contains(tagValue));
    }
}
