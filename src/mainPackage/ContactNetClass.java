package mainPackage;

import exceptions.*;
import group.GroupClass;
import group.GroupInternal;
import group.GroupSafe;
import message.Message;
import message.MessageClass;
import user.UserClass;
import user.UserInternal;
import user.UserSafe;
import dataStructures.*;

public class ContactNetClass implements ContactNet {

    List<UserInternal> users;
    List<GroupInternal> groups;

    public ContactNetClass() {
        users = new DoublyLinkedList<>();
        groups = new DoublyLinkedList<>();
    }

    @Override
    public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
        UserInternal newUser = new UserClass(login, name, age, address, profession);

        if (getUser(login) != null)
            throw new UserExists();
        else
            users.addFirst(newUser);
    }

    @Override
    public UserSafe showUser(String login) throws UserNotExists {
        UserInternal user = getUser(login);

        if (user == null)
           throw new UserNotExists();

        return user;
    }

    @Override
    public void insertContact(String login1, String login2) throws UserNotExists, ContactExists {
        UserInternal user1 = getUser(login1);
        UserInternal user2 = getUser(login2);

        if (user1 == null || user2 == null)
            throw new UserNotExists();

        user1.addContact(user2);
        user2.addContact(user1);
    }

    @Override
    public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {
        UserInternal user1 = getUser(login1);
        UserInternal user2 = getUser(login2);

        if (user1 == null || user2 == null)
            throw new UserNotExists();

        if (user1 == user2)
            throw new ContactNotRemoved();

        user1.removeContact(user2);
        user2.removeContact(user1);
    }

    @Override
    public Iterator<UserSafe> listContacts(String login) throws UserNotExists, NoContacts {
        UserInternal user = getUser(login);

        if (user == null)
            throw new UserNotExists();

        return user.listContacts();
    }

    @Override
    public void insertGroup(String group, String description) throws GroupExists {
        GroupInternal newGroup = new GroupClass(group, description);

        if (getGroup(group) != null)
            throw new GroupExists();

        groups.addFirst(newGroup);
    }

    @Override
    public GroupSafe showGroup(String group) throws GroupNotExists {
        GroupInternal grp = getGroup(group);

        if (grp == null)
            throw new GroupNotExists();

        return grp;
    }

    @Override
    public void removeGroup(String group) throws GroupNotExists {
        GroupInternal grp = getGroup(group);

        if (grp == null)
            throw new GroupNotExists();

        groups.remove(grp);
    }

    @Override
    public void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists {
        UserInternal user = getUser(login);

        if (user == null)
            throw new UserNotExists();

        GroupInternal grp = getGroup(group);

        if (grp == null)
            throw new GroupNotExists();

        grp.subscribeUser(user);
    }

    @Override
    public void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists {
        UserInternal user = getUser(login);

        if (user == null)
            throw new UserNotExists();

        GroupInternal grp = getGroup(group);

        if (grp == null)
            throw new GroupNotExists();

        grp.removeSubscriptionFromUser(user);
    }

    @Override
    public Iterator<UserSafe> listParticipants(String group) throws GroupNotExists, NoParticipants {
        GroupInternal grp = getGroup(group);

        if (grp == null)
            throw new GroupNotExists();

        return grp.listParticipants();
    }

    @Override
    public void insertMessage(String login, String title, String text, String url) throws UserNotExists {
        UserInternal user = getUser(login);

        if (user == null)
            throw new UserNotExists();

        user.insertMessage(new MessageClass(title, text, url));
    }

    @Override
    public Iterator<Message> listContactMessages(String login1, String login2) throws UserNotExists, ContactNotExists, NoContactMessages {
        UserInternal user1 = getUser(login1);
        UserInternal user2 = getUser(login2);

        if (user1 == null || user2 == null)
            throw new UserNotExists();

        return user1.listMessages(user2);
    }

    @Override
    public Iterator<Message> listGroupMessages(String group, String login) throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
        UserInternal user = getUser(login);

        if (user == null)
            throw new UserNotExists();

        GroupInternal grp = getGroup(group);

        if (grp == null)
            throw new GroupNotExists();

        return grp.listGroupMessages(user);
    }

    private UserInternal getUser(String login){
        Iterator<UserInternal> it = users.iterator();

        UserInternal next;
        while (it.hasNext()) {
            next = it.next();
            if (next.getLogin().equals(login))
                return next;
        }

        return null;
    }

    private GroupInternal getGroup(String name) {
        Iterator<GroupInternal> it = groups.iterator();

        GroupInternal next;
        while (it.hasNext()) {
            next = it.next();
            if (next.getGroupName().equals(name))
                return next;
        }

        return null;
    }
}
