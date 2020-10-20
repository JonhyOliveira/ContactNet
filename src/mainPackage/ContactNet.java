package mainPackage;

import dataStructures.Iterator;
import exceptions.*;
import group.GroupSafe;
import message.Message;
import user.UserSafe;

public interface ContactNet {

    /**
     * Adds a new user to the system
     * @param login the login credential for the user
     * @param name the name of the user
     * @param age the age of the user
     * @param address the user address
     * @param profession the user profession
     * @throws UserExists if a user with the same credential already exists
     */
    void insertUser(String login, String name, int age, String address, String profession)
            throws UserExists;

    /**
     * Shows the info on the user
     * @param login
     * @return
     * @throws UserNotExists
     */
    UserSafe showUser(String login) throws UserNotExists;

    void insertContact(String login1, String login2)
                    throws UserNotExists, ContactExists;

    void removeContact(String login1, String login2)
                            throws UserNotExists, ContactNotExists, ContactNotRemoved;

    Iterator<UserSafe> listContacts(String login)
                                    throws UserNotExists, NoContacts;

    void insertGroup(String group, String description) throws GroupExists;

    GroupSafe showGroup(String group) throws GroupNotExists;

    void removeGroup(String group) throws GroupNotExists;

    void subscribeGroup(String login, String group)
                                            throws UserNotExists, GroupNotExists, SubscriptionExists;

    void removeSubscription(String login, String group)
                                                throws UserNotExists, GroupNotExists, SubscriptionNotExists;

    Iterator<UserSafe> listParticipants(String group)
                                                        throws GroupNotExists, NoParticipants;

    void insertMessage(String login, String title, String text, String url)
                                                            throws UserNotExists;

    Iterator<Message> listContactMessages(String login1, String login2)
                                                                throws UserNotExists, ContactNotExists, NoContactMessages;

    Iterator<Message> listGroupMessages(String group, String login)
                                                                        throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;
}
