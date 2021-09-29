package madzi.apps.tagit.service.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import madzi.apps.tagit.domain.DefaultResource;
import madzi.apps.tagit.domain.Resource;
import madzi.apps.tagit.service.ResourceService;
import madzi.apps.tagit.service.xml.model.XmlResource;
import madzi.apps.tagit.service.xml.model.XmlResources;

public class XmlResourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(XmlResourceLoader.class);

    private final Path path;
    private final ResourceService resourceService;

    public XmlResourceLoader(final Path resPath, final ResourceService resService) {
        this.path = resPath;
        this.resourceService = resService;
    }

    public void load() {
        final XmlMapper xmlMapper = new XmlMapper();
        try {
            final XmlResources resources = xmlMapper.readValue(path.toFile(), XmlResources.class);
            resources.getResources().forEach(resource -> resourceService.add(fromXml(resource)));
        } catch (final IOException ioException) {
            logger.error("Unable to load XML database: {} with resources", path.toAbsolutePath(), ioException);
        }
    }

    public void save() {
        final XmlMapper xmlMapper = new XmlMapper();
        final XmlResources xmlResources = new XmlResources();
        try {
            List<XmlResource> items = new ArrayList<>();
            xmlResources.setResources(items);
            xmlMapper.writeValue(path.toFile(), xmlResources);
        } catch (final IOException ioException) {
            logger.error("Unable to save XML database: {} with resources", path.toAbsolutePath(), ioException);
        }
    }

    public Resource fromXml(final XmlResource xmlResource) {
        final Map<String, String> tags = new HashMap<>();
        xmlResource.getTags().forEach(tag -> tags.put(tag.getName(), tag.getValue()));

        return DefaultResource.create(xmlResource.getLocation(), tags);
    }

    public XmlResource toXml(final Resource resource) {
        final XmlResource xmlResource = new XmlResource();
        xmlResource.setLocation(resource.location());

        xmlResource.setTags(null);

        return new XmlResource();
    }
}
