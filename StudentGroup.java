package application;

public class StudentGroup {

    private String groupId;
    private String name;

    public StudentGroup(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return groupId + " - " + name;
    }
}