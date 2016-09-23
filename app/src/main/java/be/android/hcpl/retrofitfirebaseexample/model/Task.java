package be.android.hcpl.retrofitfirebaseexample.model;

public class Task {

    private String title;

    private String description;

    public Task() {
    }

    public Task(
            final String title,
            final String description) {

        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
