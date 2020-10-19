package user;

import dataStructures.*;
import exceptions.*;
import group.GroupInternal;
import message.Message;

/**
 * For internal use.
 */
public interface UserInternal extends UserSafe {

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
     * Lists the contacts of the user
     * @return an iterator for the contacts of the user
     * @throws NoContacts if the user has no contacts
     */
    Iterator<UserSafe> listContacts()
        throws NoContacts;

    /**
     * Requests a lists of the messages received by the user
     * @param contact user requesting access to this user messages
     * @return an iterator for the messages received by this user
     * @throws NoContactMessages if the user has received no messages
     */
    Iterator<Message> listMessages(UserInternal contact)
        throws ContactNotExists, NoContactMessages;
}
