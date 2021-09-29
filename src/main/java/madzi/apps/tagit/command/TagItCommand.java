package madzi.apps.tagit.command;

import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "tagit", description = "tag your resources")
public class TagItCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("USAGE: tagit <command>");
        return 0;
    }
}
