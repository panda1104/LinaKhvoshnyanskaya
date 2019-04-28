package hw5.Issue;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueWithFiles extends Issue {

    private final Files files[];

    @JsonCreator
    public IssueWithFiles(
            @JsonProperty("issue") final Issue issue,
            @JsonProperty("files") final Files[] files)
    {
        super(issue);
        this.files = files;
    }

    @JsonProperty("files")
    public Files[] getFiles() {
        return files;
    }
}
