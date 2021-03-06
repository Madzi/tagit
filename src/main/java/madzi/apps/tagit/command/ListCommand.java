package madzi.apps.tagit.command;

import java.util.concurrent.Callable;

import madzi.apps.tagit.domain.Resource;
import madzi.apps.tagit.service.ResourceService;
import picocli.CommandLine;

@CommandLine.Command(name = "list", description = "Show all stored resources")
public class ListCommand implements Callable<Integer> {

    private final ResourceService resourceService;

    public ListCommand(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public Integer call() {
        for (Resource resource : resourceService.findAll()) {
            System.out.println(resource.location());
            resource.tags().forEach((key, value) -> {
                System.out.println(" - " + key + " : " + value);
            });
        }
        return 0;
    }
}
