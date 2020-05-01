package ch.simonpeier.Bildergalerie;

public class Data {
    private String name;
    private String tagName;
    private String description;

    public Data(String name, String tagName, String description) {
        this.name = name;
        this.tagName = tagName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
