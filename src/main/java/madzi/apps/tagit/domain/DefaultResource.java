package madzi.apps.tagit.domain;

import java.net.URI;
import java.util.Map;

public class DefaultResource implements Resource {

    private final URI location;
    private final Map<String, String> tags;

    private DefaultResource(final URI resLocation, final Map<String, String> resTags) {
        location = resLocation;
        tags = resTags;
    }

    @Override
    public URI location() {
        return location;
    }

    @Override
    public Map<String, String> tags() {
        return tags;
    }

    public static Resource create(final URI resLocation, final Map<String, String> resTags) {
        return new DefaultResource(resLocation, resTags);
    }
}
