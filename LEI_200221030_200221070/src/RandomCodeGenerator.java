import java.util.UUID;

public class RandomCodeGenerator {
    public static String generateUniqueCode() {
        UUID unique = UUID.randomUUID();
        return (String)unique.toString();
    }
}
