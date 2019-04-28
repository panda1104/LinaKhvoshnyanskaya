package hw5.Issue;

import com.fasterxml.jackson.annotation.*;
import hw5.Project.Project;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Issue {

    private final int id;
    private final String summary;
    private final String description;
    private final Project project;
    private final IssueField category;
    private final LabeledIssueField severity;
    private final LabeledIssueField priority;
    private final Status status;

    @JsonCreator
    public Issue(@JsonProperty("id") final int id,
                 @JsonProperty("summary") final String summary,
                 @JsonProperty("description") final String description,
                 @JsonProperty("project") final Project project,
                 @JsonProperty("category") final IssueField category,
                 @JsonProperty("severity") final LabeledIssueField severity,
                 @JsonProperty("priority") final LabeledIssueField priority,
                 @JsonProperty("status") final Status status
    )
    {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.project = project;
        this.category = category;
        this.severity = severity;
        this.priority = priority;
        this.status = status;
    }

    public Issue(Issue other) {
        this(
                other.id,
                other.summary,
                other.description,
                other.project,
                other.category,
                other.severity,
                other.priority,
                other.status);
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("project")
    public Project getProject() {
        return project;
    }

    @JsonProperty("category")
    public IssueField getCategory() {
        return category;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("severity")
    public LabeledIssueField getSeverity() {
        return severity;
    }

    @JsonProperty("priority")
    public LabeledIssueField getPriority() {
        return priority;
    }

}