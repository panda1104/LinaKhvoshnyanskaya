package hw5.Issue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Files {

    private final String name;
    private final String content;

    @JsonCreator
    public Files(
            @JsonProperty("name") final String name,
            @JsonProperty("content") final String content)
    {
        this.name = name;
        this.content = content;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }
}