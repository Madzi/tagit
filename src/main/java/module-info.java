module madzi.apps.tag {
    requires java.base;
    requires java.desktop;
    requires java.xml;

    requires org.slf4j;
    requires com.fasterxml.jackson.dataformat.xml;
    requires info.picocli;

    opens madzi.apps.tagit.command to info.picocli;
    opens madzi.apps.tagit.service.xml to com.fasterxml.jackson.databind;
    opens madzi.apps.tagit.service.xml.model to com.fasterxml.jackson.databind;

    exports madzi.apps.tagit;
}
