package madzi.apps.tagit.command;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;

import madzi.apps.tagit.domain.DefaultResource;
import madzi.apps.tagit.service.ResourceService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "add", description = "Add tag to resource")
public class AddCommand implements Callable<Integer> {

    @Parameters(index = "0", description = "resource")
    private Path path;

    @Option(names = {"-t", "--tag"}, description = "Tag(s) in form key=value for resoruce")
    private Map<String, String> tags;

    private final ResourceService resourceService;

    public AddCommand(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public Integer call() throws Exception {
        final var location = path.normalize().toUri();
        final var optResource = resourceService.find(location);
        optResource.ifPresentOrElse(resource -> tags.forEach(resource::addTag), () -> {
            resourceService.add(DefaultResource.create(path.normalize().toUri(), tags));
        });
        System.out.println("Path: " + location);
        if (tags != null) {
            tags.forEach((key, value) -> {
                System.out.println(" - " + key + " : " + value);
            });
        }
        return 0;
    }
}
