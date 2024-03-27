// currently not in use

package RoomieRoster.UI.RecyclerViews;

public class SingleChore {
    String choreTitle;
    String assignedTo;

    public SingleChore(String title, String assignment) {
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
