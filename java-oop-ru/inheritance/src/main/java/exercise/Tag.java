package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    protected String tagName;
    protected Map<String, String> attributes;

    public Tag(String tagName) {
        this.tagName = tagName;
        this.attributes = Map.of();
    }

    public void setAttribute(String attributeName, String attributeValue) {
        attributes = Map.of(attributeName, attributeValue);
    }

    public String render() {
        String attributeString = attributes.entrySet().stream()
                .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(" "));
        return "<" + tagName + (attributeString.isEmpty() ? "" : " " + attributeString) + ">";
    }
}
// END
