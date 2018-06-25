package me.paulhobbel.discordrp.common;

public class Modpack {
    private String projectId;
    private String name;

    public Modpack(String projectId, String name) {
        this.projectId = projectId;
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }
}
