package madzi.apps.tagit.command;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

import madzi.apps.tagit.service.ResourceService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "remove", description = "Remove tag or resource")
public class RemoveCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "resource")
    private Path path;

    @Option(names = {"-t", "--tag"}, description = "Tag(s) key(s)")
    private List<String> tags;

    private final ResourceService resourceService;

    public RemoveCommand(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public Integer call() throws Exception {
        final var location = path.normalize().toUri();
        final var optResource = resourceService.find(location);
        optResource.ifPresentOrElse(resource -> {
            if (tags != null) {
                if (!tags.isEmpty()) {
                    tags.forEach(tag -> resource.tags().remove(tag));
                }
            } else {
                resourceService.remove(resource);
            }
        }, () -> {
            System.out.println("Resource " + location + " not found");
        });
        return 0;
    }
}
