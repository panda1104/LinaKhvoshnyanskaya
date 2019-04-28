package hw5.User;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private final int id;
    private final String name;
    private final String realName;
    private final String email;

    @JsonCreator
    public User(
            @JsonProperty("id") final int id,
            @JsonProperty("name") final String name,
            @JsonProperty("real_name") final String realName,
            @JsonProperty("email") final String email
    ) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.email = email;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty("username")
    public String getName() {
        return name;
    }

    @JsonProperty("real_name")
    public String getRealName() {
        return realName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
}
