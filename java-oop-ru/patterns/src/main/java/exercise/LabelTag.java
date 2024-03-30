package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String labelText;
    private TagInterface innerTag;

    public LabelTag(String labelText, TagInterface innerTag) {
        this.labelText = labelText;
        this.innerTag = innerTag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", labelText, innerTag.render());
    }
}

// END
