@Getter
@ToString
@NoArgsConstructor
@AllargsConstructor
public class UserRegisterRequestDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("number")
    private String number;
    @JsonProperty("password")
    private String password;
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    @JsonProperty("location")
    private String location;
}
