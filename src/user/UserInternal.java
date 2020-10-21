package user;

import dataStructures.Iterator;
import exceptions.*;
import group.GroupInternal;
import message.Message;

public interface UserInternal extends UserSafe{
    /**
     * Subscribes the user to a group
     * @throws SubscriptionExists if the user is already subscribed to the group
     */
    void subscribeGroup(GroupInternal group)
        throws SubscriptionExists;

    /**
     * Removes the subscription of the user to the group
     * @throws SubscriptionNotExists if the user is not already subscribed to the group
     */
    void removeSubscriptionGroup(GroupInternal group)
        throws SubscriptionNotExists;

    /**
     * Adds a contact to the user contact list
     * @param contact the contact to add
     * @throws ContactExists if the contact is already in the user contact list
     */
    void addContact(UserInternal contact)
        throws ContactExists;

    /**
     * Removes a contact from the user contact list
     * @param contact the contact to remove
     * @throws ContactNotExists if the contact is not in the user contact list
     */
    void removeContact(UserInternal contact)
        throws ContactNotExists;

    /**
     * Lists the contacts of the user
     * @return an iterator for the contacts of the user
     * @throws NoContacts if the user has no contacts
     */
    Iterator<UserSafe> listContacts()
        throws NoContacts;

    /**
     * Adds and sends a message to the user contacts
     * @param message message to add and send
     */
    void insertMessage(Message message);

    /**
     * Adds a message to the user received messages
     * @param message message to add
     */
    void addMessage(Message message);

    /**
     * Requests a lists of the messages received by the user
     * @param contact user requesting access to this user messages
     * @return an iterator for the messages received by this user
     * @throws NoContactMessages if the user has received no messages
     */
    Iterator<Message> listMessages(UserInternal contact)
        throws ContactNotExists, NoContactMessages;
}
