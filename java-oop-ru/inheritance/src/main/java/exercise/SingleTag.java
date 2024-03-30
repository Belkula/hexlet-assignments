package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    private Map<String, String> attributes;

    public SingleTag(String tagName, Map<String, String> attributes) {
        super(tagName);
        this.attributes = attributes;
    }

    @Override
    public String render() {
        StringBuilder attributeBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attributeBuilder.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        return "<" + tagName + attributeBuilder.toString() + ">";
    }

    @Override
    public String toString() {
        return render();
    }
}
// END
