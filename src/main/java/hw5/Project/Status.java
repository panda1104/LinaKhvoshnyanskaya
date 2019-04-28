package hw5.Project;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    private final int id;
    private  final String name;
    private final String label;

    @JsonCreator
    public Status(
            @JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("label") final String label
    )
    {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }
}
