package carrentalsystem;

public class Feedback {
    private User user;
    private String feedback;

    public Feedback(User user, String feedback) {
        this.user = user;
        this.feedback = feedback;
    }

    public User getUser() {
        return user;
    }

    public String getFeedback() {
        return feedback;
    }
    
}
