package group;

import exceptions.*;
import message.Message;
import user.UserInternal;
import user.UserSafe;

import java.util.Iterator;

public interface GroupInternal extends GroupSafe {

    /**
     * Subscribes a user to the group
     * @param user the user to subscribe to the group
     * @throws SubscriptionExists if the subscription already exists
     */
    void subscribeUser(UserInternal user)
        throws SubscriptionExists;

    /**
     * Removes the subscription of a user to the group
     * @param user the user to remove from the group
     * @throws SubscriptionNotExists if the subscription does not exist
     */
    void removeSubscriptionFromUser(UserInternal user)
        throws SubscriptionNotExists;

    /**
     * Lists the participants of the group
     * @return an iterator for the participants of the group
     * @throws NoParticipants if there are no participants
     */
    Iterator<UserSafe> listParticipants()
        throws NoParticipants;

    /**
     * Lists the messages from the subscribed users
     * @param groupMember the group member requesting access to the group messages
     * @return an iterator for the group messages
     * @throws SubscriptionNotExists if the groupMember is not subscribed
     */
    Iterator<Message> listGroupMessages(UserInternal groupMember)
        throws SubscriptionNotExists;

}
