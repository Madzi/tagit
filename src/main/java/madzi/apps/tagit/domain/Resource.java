package madzi.apps.tagit.domain;

import java.net.URI;
import java.util.Map;

/**
 * Served resource.
 */
public interface Resource {

    /**
     * Returns ocation of the resource.
     *
     * @return location the location of the resource
     */
    URI location();

    /**
     * Returns associated with resource tags.
     *
     * @return tags the set of tags with values
     */
    Map<String, String> tags();

    /**
     * Returns value associated with provided tag.
     *
     * @param tag the tag's name
     * @return value the tag's value
     */
    String getTag(String tag);

    /**
     * Assign provided tag with value to resource.
     *
     * @param tag name of tag
     *O @param value value for tag
     */
    void addTag(String tag, String value);

    /**
     * Check whether if provided tag connected to resource.
     *
     * @param tag name of tag
     * @return {@code true} if tag present
     */
    boolean hasTag(String tag);

    /**
     * Removes provided tag with description from resource.
     *
     * @param tag name of tag
     */
    void removeTag(String tag);
}
