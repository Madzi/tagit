package madzi.apps.tagit.command;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import madzi.apps.tagit.domain.Resource;
import madzi.apps.tagit.service.ResourceService;
import picocli.CommandLine;

@CommandLine.Command(name = "ls", description = "Show resources with tags")
public class LsCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "path", defaultValue = ".")
    private Path path;

    @CommandLine.Option(names = {"-R", "--recursive"}, description = "Include subfolders or not")
    private boolean recurse = false;

    private final ResourceService resourceService;

    public LsCommand(final ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(path);
        Files.walk(path.normalize(), recurse ? 100 : 1, FileVisitOption.FOLLOW_LINKS).forEach(filePath -> {
            final Optional<Resource> optResource = resourceService.find(filePath.toUri());
            if (optResource.isPresent()) {
                final Resource resource = optResource.get();
                System.out.println("[R] " + resource.location());
                final Map<String, String> tags = resource.tags();
                if (tags != null && !tags.isEmpty()) {
                    tags.forEach((key, value) -> {
                        System.out.println(" === " + key + " : " + value);
                    });
                }
            } else {
                System.out.println("[ ] " + filePath.toUri());
            }
        });
        return 0;
    }
}
