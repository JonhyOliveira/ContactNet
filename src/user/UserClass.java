package user;

import dataStructures.Iterator;
import dataStructures.List;
import exceptions.*;
import group.GroupInternal;
import message.Message;

public class UserClass implements UserInternal {

    private String login, name, address, profession;
    private int age;

    private List<UserInternal> contacts;
    private List<GroupInternal> groups;

    private List<Message> receivedMessages;

    public UserClass(String login, String name, int age, String address, String profession) {
        this.login = login;
        this.name = name;
        this.age = age;
        this.address = address;
        this.profession = profession;
    }

    @Override
    public void subscribeGroup(GroupInternal group) throws SubscriptionExists {
        groups.addFirst(group);
    }

    @Override
    public void removeSubscriptionGroup(GroupInternal group) throws SubscriptionNotExists {
        if (!groups.remove(group))
            throw new SubscriptionNotExists();
    }

    @Override
    public void addContact(UserInternal contact) throws ContactExists {

    }

    @Override
    public void removeContact(UserInternal contact) throws ContactNotExists {

    }

    @Override
    public Iterator<UserSafe> listContacts() throws NoContacts {
        return null;
    }

    @Override
    public void sendMessage(Message message) {

        Iterator<UserInternal> it = contacts.iterator();
        while (it.hasNext()) {
            UserInternal contact = it.next();
            contact.addMessage(message);
        }
        this.addMessage(message);
    }

    @Override
    public void addMessage(Message message) {
        receivedMessages.addFirst(message);
    }

    @Override
    public Iterator<Message> listMessages(UserInternal contact) throws ContactNotExists, NoContactMessages {
        return receivedMessages.iterator();
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getProfession() {
        return profession;
    }
}
