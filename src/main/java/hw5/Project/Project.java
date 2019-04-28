package hw5.Project;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {

    private final int id;
    private final String name;
    private final String description;
    private final Status status;

    @JsonCreator
    public Project(
            @JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("description") final String description,
            @JsonProperty("status") final Status status
    )
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }
}
