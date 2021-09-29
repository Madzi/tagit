package madzi.apps.tagit.command;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "ls", description = "Show resources with tags")
public class LsCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "path")
    private Path path;

    @CommandLine.Option(names = {"-R", "--recursive"}, description = "Include subfolders or not")
    private boolean recurse = false;

    @Override
    public Integer call() throws Exception {
        Files.walk(path, recurse ? 100 : 1, FileVisitOption.FOLLOW_LINKS).forEach(filePath -> {
            System.out.println(" -> " + filePath);
        });
        return 0;
    }
}
