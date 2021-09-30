package madzi.apps.tagit.service.loader.xml;

import java.util.HashMap;
import java.util.Map;

import madzi.apps.tagit.domain.DefaultResource;
import madzi.apps.tagit.domain.Resource;

public class ResourceBuilder {

    private String location;
    private Map<String, String> tags = new HashMap<>();

    private ResourceBuilder() {
    }

    public Resource build() {
        return DefaultResource.create(location, tags);
    }

    public ResourceBuilder location(final String location) {
        this.location = location;
        return this;
    }

    public ResourceBuilder tag(final String name, final String value) {
        tags.put(name, value);
        return this;
    }

    public static ResourceBuilder create() {
        return new ResourceBuilder();
    }
}
