package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabeledIssueField extends IssueField {

    private final String label;

    @JsonCreator
    public LabeledIssueField(
            @JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("label") final String label) {
        super(id, name);
        this.label = label;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }
}
