// BEGIN
package exercise.dto;

public abstract class BasePage {
    private String flashMessage;

    public BasePage() {}

    public BasePage(String flashMessage) {
        this.flashMessage = flashMessage;
    }

    public String getFlashMessage() {
        return flashMessage;
    }

    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }
}

// END
