package user;

import dataStructures.DoublyLinkedList;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.SinglyLinkedList;
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

        contacts = new DoublyLinkedList<>();
        groups = new DoublyLinkedList<>();
        receivedMessages = new DoublyLinkedList<>();

    }

    @Override
    public void subscribeGroup(GroupInternal group) {
        groups.addFirst(group);
    }

    @Override
    public void removeSubscriptionGroup(GroupInternal group) {
        groups.remove(group);
    }

    @Override
    public void addContact(UserInternal contact) throws ContactExists {

        if (hasContact(contact) || this.equals(contact))
            throw new ContactExists();

        contacts.addFirst(contact);
    }

    @Override
    public void removeContact(UserInternal contact) throws ContactNotExists {
        if (!hasContact(contact))
            throw new ContactNotExists();

        contacts.remove(contact);
    }

    @Override
    public Iterator<UserSafe> listContacts() throws NoContacts {
    	List <UserSafe> contactsSafe = new SinglyLinkedList<>();
        if (contacts.size() <= 0)
            throw new NoContacts();
        Iterator<UserInternal> itInternal = contacts.iterator();
        while(itInternal.hasNext()) {
        	contactsSafe.addLast(itInternal.next());
        }

        return contactsSafe.iterator();
    }

    @Override
    public void insertMessage(Message message) {

        // send message to the user contacts
        Iterator<UserInternal> contactsIt = contacts.iterator();
        while (contactsIt.hasNext()) {
            contactsIt.next().addMessage(message);
        }
        // send message to the user groups
        Iterator<GroupInternal> groupsIt = groups.iterator();
        while (groupsIt.hasNext()) {
            groupsIt.next().addMessage(message);
        }

        // add message to the user messages
        this.addMessage(message);
    }

    @Override
    public void addMessage(Message message) {
        receivedMessages.addFirst(message);
    }

    @Override
    public Iterator<Message> listMessages(UserInternal contact) throws ContactNotExists, NoContactMessages {

        if (!hasContact(contact))
            throw new ContactNotExists();

        if (receivedMessages.isEmpty())
            throw new NoContactMessages();

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

    private boolean hasContact(UserInternal contact) {
        if (this.equals(contact))
            return true;

        Iterator<UserInternal> it = contacts.iterator();

        UserInternal next;
        while (it.hasNext()) {
            next = it.next();
            if (next.equals(contact))
                return true;
        }

        return false;
    }
}
