package madzi.apps.tagit.service.loader.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import madzi.apps.tagit.domain.Resource;
import madzi.apps.tagit.service.loader.ResourceLoader;

public class StaxResourceLoader implements ResourceLoader {

    private static final Logger logger = LoggerFactory.getLogger(StaxResourceLoader.class);

    private static final String ROOT_ELEMENT = "resources";
    private static final String RESOURCE_ELEMENT = "resource";
    private static final String LOCATION_ELEMENT = "location";
    private static final String TAGS_ELEMENT = "tags";
    private static final String TAG_ELEMENT = "tag";
    private static final String TAG_NAME_ELEMENT = "name";
    private static final String TAG_VALUE_ELEMENT = "value";

    private final Path path;

    public StaxResourceLoader(final Path resPath) {
        this.path = resPath;
    }

    public void load1() throws XMLStreamException, IOException {
    }

    @Override
    public Iterable<Resource> load() {
        final List<Resource> resources = new ArrayList<>();
        ResourceBuilder resourceBuilder = ResourceBuilder.create();
        String tagName = "undefined";
        String tagValue = null;
        try {
            final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            final XMLEventReader reader = xmlInputFactory.createXMLEventReader(Files.newInputStream(path, StandardOpenOption.READ));

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case ROOT_ELEMENT:
                            break;
                        case RESOURCE_ELEMENT:
                            resourceBuilder = ResourceBuilder.create();
                            break;
                        case LOCATION_ELEMENT:
                            nextEvent = reader.nextEvent();
                            resourceBuilder.location(nextEvent.asCharacters().getData());
                        case TAGS_ELEMENT:
                            break;
                        case TAG_ELEMENT:
                            break;
                        case TAG_NAME_ELEMENT:
                            nextEvent = reader.nextEvent();
                            tagName = nextEvent.asCharacters().getData();
                            break;
                        case TAG_VALUE_ELEMENT:
                            nextEvent = reader.nextEvent();
                            tagValue = nextEvent.asCharacters().getData();
                            break;
                        default:
                            logger.warn("Unknown opening tag {} into resources storage: {}", startElement.getName(), path.toAbsolutePath());
                    }
                } else if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    switch (endElement.getName().getLocalPart()) {
                        case ROOT_ELEMENT:
                            break;
                        case RESOURCE_ELEMENT:
                            resources.add(resourceBuilder.build());
                            break;
                        case LOCATION_ELEMENT:
                            break;
                        case TAGS_ELEMENT:
                            break;
                        case TAG_ELEMENT:
                            resourceBuilder.tag(tagName, tagValue);
                            break;
                        case TAG_NAME_ELEMENT:
                            break;
                        case TAG_VALUE_ELEMENT:
                            break;
                        default:
                            logger.warn("Unknown closing tag {} into resources storage: {}", endElement.getName(), path.toAbsolutePath());
                    }
                }
            }
        } catch (XMLStreamException | IOException exception) {
            logger.error("Unable to load resources storage: {}", path, exception);
        }

        return resources;
    }

    @Override
    public void save(Iterable<Resource> resources) {
        try {
            final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            final XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(Files.newOutputStream(path, StandardOpenOption.WRITE));
            xmlStreamWriter.writeStartDocument("utf-8", "1.0");
            xmlStreamWriter.writeStartElement(ROOT_ELEMENT);
            xmlStreamWriter.writeComment("This document generated automaticaly by TagIt application. Please don't change it manualy.");
            for (Resource resource : resources) {
                saveResource(xmlStreamWriter, resource);
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();
            xmlStreamWriter.close();
        } catch (XMLStreamException | IOException exception) {
            logger.error("Unable to save resources storage: {}", path, exception);
        }
    }

    private void saveResource(final XMLStreamWriter xmlStreamWriter, final Resource resource) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(RESOURCE_ELEMENT);

        xmlStreamWriter.writeStartElement(LOCATION_ELEMENT);
        xmlStreamWriter.writeCharacters(resource.location().toString());
        xmlStreamWriter.writeEndElement();

        saveTags(xmlStreamWriter, resource.tags());

        xmlStreamWriter.writeEndElement();
    }

    private void saveTags(final XMLStreamWriter xmlStreamWriter, final Map<String, String> tags) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(TAGS_ELEMENT);

        if (tags != null && !tags.isEmpty()) {
            for (Map.Entry<String, String> tag : tags.entrySet()) {
                saveTag(xmlStreamWriter, tag.getKey(), tag.getValue());
            }
        }

        xmlStreamWriter.writeEndElement();
    }

    private void saveTag(final XMLStreamWriter xmlStreamWriter, final String name, final String value) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(TAG_ELEMENT);

        xmlStreamWriter.writeStartElement(TAG_NAME_ELEMENT);
        xmlStreamWriter.writeCharacters(name);
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement(TAG_VALUE_ELEMENT);
        xmlStreamWriter.writeCharacters(value);
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeEndElement();        
    }
}
