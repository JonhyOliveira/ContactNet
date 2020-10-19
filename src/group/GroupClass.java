package group;

import exceptions.NoParticipants;
import exceptions.SubscriptionExists;
import exceptions.SubscriptionNotExists;
import message.Message;
import user.UserInternal;
import user.UserSafe;

import java.util.Iterator;

public class GroupClass implements GroupInternal {

    private String name, description;

    public GroupClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void subscribeUser(UserInternal user) throws SubscriptionExists {

    }

    @Override
    public void removeSubscriptionFromUser(UserInternal user) throws SubscriptionNotExists {

    }

    @Override
    public Iterator<UserSafe> listParticipants() throws NoParticipants {
        return null;
    }

    @Override
    public Iterator<Message> listGroupMessages(UserInternal groupMember) throws SubscriptionNotExists {
        return null;
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
