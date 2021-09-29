package madzi.apps.tagit;

public class TagItApp {

    public static void main(final String... args) {
        final var configuration = new TagItConfiguration();
        configuration.init();
        int exitCode = configuration.getCommandLine().execute(args);
        configuration.stop();
        System.exit(exitCode);
    }
}
