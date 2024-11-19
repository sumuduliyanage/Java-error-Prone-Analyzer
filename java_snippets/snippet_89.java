@Getter
@Setter
public class CustomerDTO {
    private Optional<String> notificationId;
    private Optional<String> notificationMessage;
    private Optional<LocalDateTime> notificationDate;
}
