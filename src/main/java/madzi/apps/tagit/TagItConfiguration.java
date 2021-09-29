package madzi.apps.tagit;

import java.nio.file.Paths;
import madzi.apps.tagit.command.AddCommand;
import madzi.apps.tagit.command.ListCommand;
import madzi.apps.tagit.command.LsCommand;
import madzi.apps.tagit.command.TagItCommand;
import madzi.apps.tagit.service.InMemoryResourceService;
import madzi.apps.tagit.service.ResourceService;
import madzi.apps.tagit.service.xml.XmlResourceLoader;
import picocli.CommandLine;

public class TagItConfiguration {

    private ResourceService resourceService;
    private XmlResourceLoader xmlResourceLoader;

    public void init() {
        final var path = Paths.get(System.getProperty("user.home"), ".tagit").resolve("resources.xml");
        resourceService = new InMemoryResourceService();
        xmlResourceLoader = new XmlResourceLoader(path, resourceService);
        xmlResourceLoader.load();
    }

    public void stop() {
        xmlResourceLoader.save();
    }

    public CommandLine getCommandLine() {
        return new CommandLine(new TagItCommand())
                .addSubcommand("add", new AddCommand())
                .addSubcommand("list", new ListCommand(resourceService))
                .addSubcommand("ls", new LsCommand());
    }
}
