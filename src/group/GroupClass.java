package group;

import dataStructures.*;
import exceptions.NoGroupMessages;
import exceptions.NoParticipants;
import exceptions.SubscriptionExists;
import exceptions.SubscriptionNotExists;
import message.Message;
import user.UserInternal;
import user.UserSafe;

/**
 * GroupInternal implementation using data structures optimized for expected most common operations and requisites
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 */
public class GroupClass implements GroupInternal {

    private String name, description;

    private OrderedDictionary<String, UserInternal> participants; // Keep ordered by login
    
    private List<Message> groupMessages; // Keep insertion order

    public GroupClass(String name, String description) {
        this.name = name;
        this.description = description;

        participants = new AVLTree<>();
        groupMessages = new SinglyLinkedList<>();
    }

    @Override
    public void subscribeUser(UserInternal user) throws SubscriptionExists {
        if (participants.find(user.getLogin()) != null)
            throw new SubscriptionExists();

        participants.insert(user.getLogin(), user);
        user.subscribeGroup(this);
    }

    @Override
    public void removeSubscriptionFromUser(UserInternal user) throws SubscriptionNotExists {
        if (participants.find(user.getLogin()) == null)
            throw new SubscriptionNotExists();

        participants.remove(user.getLogin());
        user.removeSubscriptionGroup(this);
    }

    @Override
    public Iterator<UserInternal> listParticipants() throws NoParticipants {
        if (this.participants.size() <= 0)
            throw new NoParticipants();

        List<UserInternal> participants = new SinglyLinkedList<>();
        Iterator<Entry<String, UserInternal>> entryIterator = this.participants.iterator();

        while (entryIterator.hasNext())
            participants.addLast(entryIterator.next().getValue());

        return participants.iterator();
    }

    @Override
    public void addMessage(Message message) {
        groupMessages.addFirst(message);
    }

    @Override
    public Iterator<Message> listGroupMessages(UserInternal groupMember) throws SubscriptionNotExists, NoGroupMessages {
        if (participants.find(groupMember.getLogin()) == null)
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
