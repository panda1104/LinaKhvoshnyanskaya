package hw5.Project;

import com.fasterxml.jackson.annotation.*;

public class ProjectResponse {

    private final Project project;

    @JsonCreator
    public ProjectResponse(
            @JsonProperty("project") final Project project
    )
    {
        this.project = project;
    }

    @JsonProperty("project")
    public Project getProject() {
        return project;
    }
}
