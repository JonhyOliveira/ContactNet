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
     * @param login the login credential for the user
     * @return the user information
     * @throws UserNotExists if there is no user with the same credential
     */
    UserSafe showUser(String login) throws UserNotExists;

    /**
     * Creates a contact between the user with login1 and the user with login2
     *
     * @param login1 user to create contact
     * @param login2 other user to create contact
     * @throws UserNotExists if one of the users does not exist
     * @throws ContactExists if the contact between the user with login1 and the user with login2 already exists
     */
    void insertContact(String login1, String login2)
            throws UserNotExists, ContactExists;

    /**
     * Removes a contact between the user with login1 and the user with login2
     *
     * @param login1 user to create contact
     * @param login2 other user to create contact
     * @throws UserNotExists     if one of the users does not exist
     * @throws ContactNotExists  if the contact between the user with login1 and the user with login2 does not exist
     * @throws ContactNotRemoved if the contact could not be removed
     */
    void removeContact(String login1, String login2)
            throws UserNotExists, ContactNotExists, ContactNotRemoved;

    /**
     * @param login the login credential of the user to get the contact list from
     * @return a lexicographically ordered by login credential iterator of the user's contacts
     * @throws UserNotExists if the user does not exist
     * @throws NoContacts    if the user has no contacts
     */
    Iterator<UserSafe> listContacts(String login)
            throws UserNotExists, NoContacts;

    /**
     * Adds a new group to the system
     *
     * @param group       name of the group to add
     * @param description description of the group to add
     * @throws GroupExists if a group with the same name already exists
     */
    void insertGroup(String group, String description) throws GroupExists;

    /**
     * @param group the name of the group to search for
     * @return information on the group
     * @throws GroupNotExists if the group with the group name does not exist
     */
    GroupSafe showGroup(String group) throws GroupNotExists;

    /**
     * Removes a group from the system
     *
     * @param group the name of the group to remove
     * @throws GroupNotExists if the group does not exist
     */
    void removeGroup(String group) throws GroupNotExists;

    /**
     * Subscribes a user to a group
     *
     * @param login the login credential of the user
     * @param group the name of the group
     * @throws UserNotExists      if the user does not exist
     * @throws GroupNotExists     if the group does not exist
     * @throws SubscriptionExists if the subscription already exists
     */
    void subscribeGroup(String login, String group)
            throws UserNotExists, GroupNotExists, SubscriptionExists;

    /**
     * Removes a user from a group
     *
     * @param login the login credential of the user
     * @param group the name of the group
     * @throws UserNotExists         if the user does not exist
     * @throws GroupNotExists        if the group does not exist
     * @throws SubscriptionNotExists if the subscription does not already exist
     */
    void removeSubscription(String login, String group)
            throws UserNotExists, GroupNotExists, SubscriptionNotExists;

    /**
     * @param group the group name of the group to get the participants list from
     * @return a lexicographically ordered by login credential iterator of the group's participants
     * @throws GroupNotExists if the group does not exist
     * @throws NoParticipants if the group has no participants
     */
    Iterator<UserSafe> listParticipants(String group)
            throws GroupNotExists, NoParticipants;

    /**
     * Inserts a new message from the user in the system
     *
     * @param login the login credential of the user
     * @param title the title of the message
     * @param text  the text of the message
     * @param url   the url of the message
     * @throws UserNotExists if the user does not exist
     */
    void insertMessage(String login, String title, String text, String url)
            throws UserNotExists;

    /**
     * Makes a request for a list of the messages of the user with login credential login1, the request is made by one
     * of its contacts (the user with login credential login2)
     *
     * @param login1 the login credential of the user to get the contacts from
     * @param login2 the login credential of the user asking for the contacts
     * @return an iterator of the messages of the user with login credential login1
     * @throws UserNotExists     if one of the users does not exist
     * @throws ContactNotExists  if the user with login2 is not a contact of the user with login1
     * @throws NoContactMessages if the user does not have messages
     */
    Iterator<Message> listContactMessages(String login1, String login2)
            throws UserNotExists, ContactNotExists, NoContactMessages;

    /**
     * Makes a request for a list of the messages on the group, the request is made by one of its participants (the user
     * with login credential login2)
     *
     * @param group the name of the group
     * @param login the login credential of the participant
     * @return an iterator of the messages on the group
     * @throws GroupNotExists        if the group does not exist
     * @throws UserNotExists         if the user does not exist
     * @throws SubscriptionNotExists if the subscription does not exist (the user is not a participant)
     * @throws NoGroupMessages       if the group has no messages
     */
    Iterator<Message> listGroupMessages(String group, String login)
            throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;
}
