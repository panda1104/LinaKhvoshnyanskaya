package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueResponse {

    private final Issue issue;

    @JsonCreator
    public IssueResponse(
            @JsonProperty("issue") final Issue issue) {
        this.issue = issue;
    }

    @JsonProperty("issue")
    public Issue getIssue() {
        return issue;
    }
}
