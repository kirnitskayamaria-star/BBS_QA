package user;
import utils.PropertyReader;

public class UserFactory {
    public static User withBasePermission() {
        return new User(PropertyReader.getProperty("prcy.user"),
                PropertyReader.getProperty("prcy.password"));
    }
}