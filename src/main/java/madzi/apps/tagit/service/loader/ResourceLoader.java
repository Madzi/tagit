package madzi.apps.tagit.service.loader;

import madzi.apps.tagit.domain.Resource;

public interface ResourceLoader {

    Iterable<Resource> load();

    void save(Iterable<Resource> resources);
}
