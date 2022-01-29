package kth.iv1201.recruitment.entity;

public class Applicant {

    private final long id;
    private final String content;

    public Applicant(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
