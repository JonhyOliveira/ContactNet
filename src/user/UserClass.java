package user;

import dataStructures.*;
import exceptions.ContactExists;
import exceptions.ContactNotExists;
import exceptions.NoContactMessages;
import exceptions.NoContacts;
import group.GroupInternal;
import message.Message;

/**
 * UserInternal implementation using data structures optimized for expected most common operations and requisites
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 */
public class UserClass implements UserInternal {

    private String login, name, address, profession;
    private int age;

    private OrderedDictionary<String, UserInternal> contacts; // Keep ordered by login
    
    private Dictionary<String, GroupInternal> groups; // Fast access
    
    private List<Message> receivedMessages; // Keep insertion order

    public UserClass(String login, String name, int age, String address, String profession) {
        this.login = login;
        this.name = name;
        this.age = age;
        this.address = address;
        this.profession = profession;

        contacts = new AVLTree<>();
        groups = new ChainedHashTable<>();
        receivedMessages = new SinglyLinkedList<>();
    }

    @Override
    public void subscribeGroup(GroupInternal group) {
        groups.insert(group.getGroupName(), group);
    }

    @Override
    public void removeSubscriptionGroup(GroupInternal group) {
        groups.remove(group.getGroupName());
    }

    @Override
    public void addContact(UserInternal contact) throws ContactExists {

        if (hasContact(contact) || this.equals(contact))
            throw new ContactExists();

        contacts.insert(contact.getLogin(), contact);
    }

    @Override
    public void removeContact(UserInternal contact) throws ContactNotExists {
        if (!hasContact(contact))
            throw new ContactNotExists();

        contacts.remove(contact.getLogin());
    }

    @Override
    public Iterator<UserInternal> listContacts() throws NoContacts {
//        List<UserSafe> contactsSafe = new SinglyLinkedList<>();
        if (contacts.size() <= 0)
            throw new NoContacts();
        
        List<UserInternal> contacts = new SinglyLinkedList<>();
        Iterator<Entry<String, UserInternal>> entryIterator = this.contacts.iterator();
        
        while(entryIterator.hasNext()) {
        	contacts.addLast(entryIterator.next().getValue());
        }
        
        return contacts.iterator();
    }

    @Override
    public void insertMessage(Message message) {

        // send message to the user contacts
        Iterator<Entry<String, UserInternal>> userIterator = this.contacts.iterator();

        while(userIterator.hasNext()) {
        	userIterator.next().getValue().addMessage(message);
        }

        // send message to the user groups
        Iterator<Entry<String, GroupInternal>> groupIterator = this.groups.iterator();
        
        while(groupIterator.hasNext()) {
        	groupIterator.next().getValue().addMessage(message);
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
        

        List<UserInternal> contacts = new SinglyLinkedList<>();
        Iterator<Entry<String, UserInternal>> entryIterator = this.contacts.iterator();
        
        while(entryIterator.hasNext()) {
        	contacts.addLast(entryIterator.next().getValue());
        }

        Iterator<UserInternal> it = contacts.iterator();

        UserInternal next;
        while (it.hasNext()) {
            next = it.next();
            if (next.equals(contact))
                return true;
        }

        return false;
    }

    @Override
    public int compareTo(UserInternal o) {
        return this.getLogin().compareTo(o.getLogin());
    }
}
