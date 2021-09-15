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
     * @return location
     */
    URI location();

    /**
     * Returns associated with resource tags.
     *
     * @return tags
     */
    Map<String, String> tags();
}
