package madzi.apps.tagit.service.xml;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import madzi.apps.tagit.domain.Resource;
import madzi.apps.tagit.service.InMemoryResourceService;
import madzi.apps.tagit.service.ResourceService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XmlResourceLoaderTest {

    @Test
    public void test() throws StreamReadException, DatabindException, IOException {
        final ResourceService resourceService = new InMemoryResourceService();
        assertNotNull(resourceService);
        final XmlResourceLoader loader = new XmlResourceLoader(Paths.get("src/test/resources", "resources.xml"), resourceService);
        assertNotNull(loader);
        loader.load();
        for (Resource resource : resourceService.findAll()) {
            assertNotNull(resource);
            assertNotNull(resource.getTag("kind"));
        }
    }

}
