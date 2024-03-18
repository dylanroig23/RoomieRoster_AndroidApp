package RoomieRoster.UI.RecyclerViews;

public class Chore {
    String choreTitle;
    String assignedTo;

    public Chore (String title, String assignment) {
        this.choreTitle = title;
        this.assignedTo = assignment;
    }

    public String getChoreTitle() {
        return choreTitle;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setChoreTitle(String choreTitle) {
        this.choreTitle = choreTitle;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
