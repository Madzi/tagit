package madzi.apps.tagit.domain;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of Resource interface.
 */
public class DefaultResource implements Resource {

    private final URI location;
    private final Map<String, String> tags;

    private DefaultResource(final URI resLocation, final Map<String, String> resTags) {
        this.location = resLocation;
        this.tags = new HashMap<String, String>(resTags);
    }

    @Override
    public URI location() {
        return location;
    }

    @Override
    public Map<String, String> tags() {
        return tags;
    }

    @Override
    public String getTag(final String tag) {
        return tags.get(tag);
    }

    @Override
    public void addTag(final String tag, final String value) {
        tags.put(tag, value);
    }

    @Override
    public boolean hasTag(final String tag) {
        return tags.containsKey(tag);
    }

    @Override
    public void removeTag(final String tag) {
        tags.remove(tag);
    }

    @Override
    public String toString() {
        final var builder = new StringBuilder("Resource{").append('\n')
                .append("- location: ").append(location).append('\n')
                .append("- tags:\n");
        tags.forEach((tag, value) -> builder.append("  * ").append(tag).append(": ").append(value).append('\n'));

        return builder.append("}\n").toString();
    }

    /**
     * Creates resource by location.
     * 
     * @param resLocation the resource's location
     * @return the simple resource
     */
    public static Resource create(final String resLocation) {
        return create(URI.create(resLocation));
    }

    /**
     * Creates resource by location and tags.
     * 
     * @param resLocation the resource's location
     * @param resTags the tags with associated values
     * @return the simple resource
     */
    public static Resource create(final String resLocation, final Map<String, String> resTags) {
        return create(URI.create(resLocation), resTags);
    }

    /**
     * Creates resource by location.
     * 
     * @param resLocation the resource's location
     * @return the simple resource
     */
    public static Resource create(final URI resLocation) {
        return create(resLocation, Collections.emptyMap());
    }

    /**
     * Creates resource by location and tags.
     * 
     * @param resLocation the resource's location
     * @param resTags the tags with associated values
     * @return the simple resource
     */
    public static Resource create(final URI resLocation, final Map<String, String> resTags) {
        return new DefaultResource(resLocation, resTags);
    }
}
