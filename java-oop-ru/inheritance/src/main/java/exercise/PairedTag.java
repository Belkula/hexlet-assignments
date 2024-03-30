package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN

public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        super(tagName);
        this.attributes = attributes;
        this.body = body;
        this.children = children;
    }

    @Override
    public String render() {
        StringBuilder attributeBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attributeBuilder.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        StringBuilder childrenBuilder = new StringBuilder();
        for (Tag child : children) {
            childrenBuilder.append(child.render());
        }
        return "<" + tagName + attributeBuilder.toString() + ">" + body + childrenBuilder.toString() + "</" + tagName + ">";
    }

    @Override
    public String toString() {
        return render();
    }
}
// END
