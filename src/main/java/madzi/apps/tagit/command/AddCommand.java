package madzi.apps.tagit.command;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "add", description = "Add tag to resource")
public class AddCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "resource")
    private Path path;

    @CommandLine.Option(names = {"-t", "--tag"}, description = "Tag(s) in form key=value for resoruce")
    private Map<String, String> tags;

    @Override
    public Integer call() throws Exception {
        System.out.println("Path: " + path);
        if (tags != null) {
            tags.forEach((key, value) -> {
                System.out.println(" - " + key + " : " + value);
            });            
        }
        return 0;
    }
}
