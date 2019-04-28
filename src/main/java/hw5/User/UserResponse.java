package hw5.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private final User user;

    @JsonCreator
    public UserResponse(
            @JsonProperty("user") User user
    )
    {
        this.user = user;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }
}
