package madzi.apps.tagit;

import java.nio.file.Paths;

import madzi.apps.tagit.command.AddCommand;
import madzi.apps.tagit.command.ListCommand;
import madzi.apps.tagit.command.LsCommand;
import madzi.apps.tagit.command.RemoveCommand;
import madzi.apps.tagit.command.TagItCommand;
import madzi.apps.tagit.service.InMemoryResourceService;
import madzi.apps.tagit.service.ResourceService;
import madzi.apps.tagit.service.loader.ResourceLoader;
import madzi.apps.tagit.service.loader.xml.StaxResourceLoader;
import picocli.CommandLine;

public class TagItConfiguration {

    private ResourceService resourceService;
    private ResourceLoader resourceLoader;

    public void init() {
        final var path = Paths.get(System.getProperty("user.home"), ".tagit").resolve("resources.xml");
        resourceService = new InMemoryResourceService();
        resourceLoader = new StaxResourceLoader(path);
        resourceService.addAll(resourceLoader.load());
    }

    public void stop() {
        resourceLoader.save(resourceService.findAll());
    }

    @SuppressWarnings("exports")
    public CommandLine getCommandLine() {
        return new CommandLine(new TagItCommand())
                .addSubcommand("add", new AddCommand(resourceService))
                .addSubcommand("list", new ListCommand(resourceService))
                .addSubcommand("ls", new LsCommand(resourceService))
                .addSubcommand("remove", new RemoveCommand(resourceService));
    }
}
