package mainPackage;

import exceptions.*;
import group.GroupInternal;
import group.GroupSafe;
import message.Message;
import user.UserInternal;
import user.UserSafe;
import dataStructures.*;

public class ContactNetClass implements ContactNet {

    List<UserInternal> users;
    List<GroupInternal> groups;

    @Override
    public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
    }

    @Override
    public UserSafe showUser(String login) throws UserNotExists {
        return null;
    }

    @Override
    public void insertContact(String login1, String login2) throws UserNotExists, ContactExists {

    }

    @Override
    public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {

    }

    @Override
    public Iterator<UserSafe> listContacts(String login) throws UserNotExists, NoContacts {
        return null;
    }

    @Override
    public void insertGroup(String group, String description) throws GroupExists {

    }

    @Override
    public GroupSafe showGroup(String group) throws GroupNotExists {
        return null;
    }

    @Override
    public void removeGroup(String group) throws GroupNotExists {

    }

    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {

    }

    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {

    }

    @Override
    public Iterator<UserSafe> listParticipants(String group) throws GroupNotExists, NoParticipants {
        return null;
    }

    @Override
    public void insertMessage(String login, String title, String text, String url) throws UserNotExists {

    }

    @Override
    public Iterator<Message> listContactMessages(String login1, String login2) throws UserNotExists, ContactNotExists, NoContactMessages {
        return null;
    }

    @Override
    public Iterator<Message> listGroupMessages(String group, String login) throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
        return null;
    }
}
