package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status extends LabeledIssueField {

    private final String color;

    @JsonCreator
    public Status(
            @JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("label") final String label,
            @JsonProperty("color") final String color)
    {
        super(id, name, label);
        this.color = color;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }
}
