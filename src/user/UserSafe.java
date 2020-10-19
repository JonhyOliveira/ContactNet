package user;

/**
 * For external use.
 */
public interface UserSafe {

    /**
     * @return the login credential of the user
     */
    String getLogin();

    /**
     * @return the user name of the user
     */
    String getName();

    /**
     * @return the age of the user
     */
    int getAge();

    /**
     * @return the location of the user
     */
    String getAddress();

    /**
     * @return the job of the user
     */
    String getProfession();
}
