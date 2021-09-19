package madzi.apps.tagit.service.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.net.URI;
import java.util.List;

public class XmlResource {

    @JacksonXmlProperty
    private URI location;
    @JacksonXmlElementWrapper(useWrapping = true)
    private List<XmlTag> tags;

    public URI getLocation() {
        return location;
    }

    public void setLocation(final URI location) {
        this.location = location;
    }

    public List<XmlTag> getTags() {
        return tags;
    }

    public void setTags(final List<XmlTag> tags) {
        this.tags = tags;
    }
}
