module madzi.apps.tag {
    requires java.base;
    requires java.desktop;
    requires java.xml;

    requires org.slf4j;
    requires info.picocli;

    opens madzi.apps.tagit.command to info.picocli;

    exports madzi.apps.tagit;
}
