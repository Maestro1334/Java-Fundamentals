package nl.inholland.exam;

public class Line {
    private final long id;
    private final String text;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Line(long id, String text){
        this.id = id;
        this.text = text;
    }
}
