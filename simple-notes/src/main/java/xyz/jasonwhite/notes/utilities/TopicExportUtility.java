package xyz.jasonwhite.notes.utilities;

import xyz.jasonwhite.notes.model.Section;
import xyz.jasonwhite.notes.model.Topic;

public class TopicExportUtility {
    
    private static final String CARRIAGE_RETURN = "\n\n";
    
    public static String generateMarkdown(Topic topic, Iterable<Section> sections) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildTopic(topic));
        sb.append(buildSections(sections));
        return sb.toString();
    }
    
    private static String buildTopic(Topic topic) {
        StringBuilder sb = new StringBuilder();
        sb.append("# " + topic.getTitle() + CARRIAGE_RETURN);
        sb.append("*" + topic.getSynopsis() + "*" + CARRIAGE_RETURN);
        return sb.toString();
    }
    
    private static String buildSections(Iterable<Section> sections) {
        StringBuilder sb = new StringBuilder();
        for(Section s : sections) {
            sb.append("## " + s.getTitle() + CARRIAGE_RETURN);
            sb.append("*" + s.getSynopsis() + "*" + CARRIAGE_RETURN);
            sb.append(s.getNotes() + "\n\n");
        }
        return sb.toString();
    }

}
