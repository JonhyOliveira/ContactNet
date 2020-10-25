package group;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.SinglyLinkedList;
import exceptions.NoParticipants;
import exceptions.SubscriptionExists;
import exceptions.SubscriptionNotExists;
import message.Message;
import user.UserInternal;
import user.UserSafe;

public class GroupClass implements GroupInternal {

    private String name, description;

    private List<UserInternal> participants;
    private List<Message> groupMessages;
    List<UserSafe> participantsSafe;

    public GroupClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void subscribeUser(UserInternal user) throws SubscriptionExists {
        if (participants.find(user) >= 0)
            throw new SubscriptionExists();

        participants.addFirst(user);
        user.subscribeGroup(this);
    }

    @Override
    public void removeSubscriptionFromUser(UserInternal user) throws SubscriptionNotExists {
        if (participants.find(user) < 0)
            throw new SubscriptionNotExists();

        participants.remove(user);
        user.removeSubscriptionGroup(this);
    }

    @Override
    public Iterator<UserSafe> listParticipants() throws NoParticipants {
    	participantsSafe = new SinglyLinkedList<>();
        if (participants.size() <= 0)
            throw new NoParticipants();
        for(int i = 0; i < participants.size(); i++) {
        	participantsSafe.addLast((UserSafe) participants.get(i));
        }
        Iterator<UserSafe> it = participantsSafe.iterator();
        
        return it;
    }

    @Override
    public void addMessage(Message message) {
        groupMessages.addFirst(message);
    }

    @Override
    public Iterator<Message> listGroupMessages(UserInternal groupMember) throws SubscriptionNotExists {
        if (participants.find(groupMember) < 0)
            throw new SubscriptionNotExists();

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
