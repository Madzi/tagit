package madzi.apps.tagit.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Resource")
public class ResourceTest {

    @Test
    @DisplayName("should be able be created")
    public void testCreation() {
        final var resource = DefaultResource.create(URI.create("https://localhost"), Collections.emptyMap());
        assertNotNull(resource);
    }
}
