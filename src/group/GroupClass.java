package group;

import dataStructures.*;
import exceptions.NoGroupMessages;
import exceptions.NoParticipants;
import exceptions.SubscriptionExists;
import exceptions.SubscriptionNotExists;
import message.Message;
import user.UserInternal;
import user.UserSafe;

public class GroupClass implements GroupInternal {

    private String name, description;

    private OrderedSequence<UserInternal> participants;
    private List<Message> groupMessages;

    public GroupClass(String name, String description) {
        this.name = name;
        this.description = description;

        participants = new OrderedSequenceClass<>();
        groupMessages = new DoublyLinkedList<>();
    }

    @Override
    public void subscribeUser(UserInternal user) throws SubscriptionExists {
        if (participants.contains(user))
            throw new SubscriptionExists();

        participants.insert(user);
        user.subscribeGroup(this);
    }

    @Override
    public void removeSubscriptionFromUser(UserInternal user) throws SubscriptionNotExists {
        if (!participants.contains(user))
            throw new SubscriptionNotExists();

        participants.remove(user);
        user.removeSubscriptionGroup(this);
    }

    @Override
    public Iterator<UserInternal> listParticipants() throws NoParticipants {
    	List<UserSafe> participantsSafe = new SinglyLinkedList<>();
        if (participants.size() <= 0)
            throw new NoParticipants();

        return participants.iterator();
    }

    @Override
    public void addMessage(Message message) {
        groupMessages.addFirst(message);
    }

    @Override
    public Iterator<Message> listGroupMessages(UserInternal groupMember) throws SubscriptionNotExists, NoGroupMessages {
        if (!participants.contains(groupMember))
            throw new SubscriptionNotExists();

        if (groupMessages.isEmpty())
            throw new NoGroupMessages();

        return groupMessages.iterator();
    }

    @Override
    public String getGroupName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
