package madzi.apps.tagit.service.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Collections;
import java.util.List;

@JacksonXmlRootElement(localName = "resources")
public class XmlResources {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "resource")
    private List<XmlResource> resources;

    public List<XmlResource> getResources() {
        return resources != null ? resources : Collections.emptyList();
    }

    public void setResources(final List<XmlResource> resources) {
        this.resources = resources;
    }
}
