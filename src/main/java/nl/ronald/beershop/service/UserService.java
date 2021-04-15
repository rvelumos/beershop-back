package nl.ronald.beershop.service;

import nl.ronald.beershop.model.User;
import nl.ronald.beershop.model.Authority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

//    public List<User> getAllUsers();
//    public User getUser(long id);
//    public void save(User User);
//    public void deleteById(long id);

    public  String createUser(User user);
    public  void updateUser(String username, User user);
    public  void deleteUser(String username);
    public  Collection<User> getUsers();
    public  Optional<User> getUser(String username);
    public  Optional<User> getUserRoles(long user_id);
    public  boolean userExists(String username);
    public  Set<Authority> getAuthorities(String username);
    public  void addAuthority(String username, String authority);
    public  void removeAuthority(String username, String authority);

}
