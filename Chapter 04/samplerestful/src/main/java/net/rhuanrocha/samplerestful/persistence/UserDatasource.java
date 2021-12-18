package net.rhuanrocha.samplerestful.persistence;

import net.rhuanrocha.samplerestful.beans.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserDatasource {

    private Map<String, User> users;

    @PostConstruct
    public void init(){
        users = new HashMap<>();
    }

    public User findById(String id){
        return users.get(id);
    }

    public User persist(User user){
        if(user.getId() == null){
            String id = UUID.randomUUID().toString();
            user.setId(id);
        }
        users.put(user.getId(),user);
        return user;
    }

    public List<User> listAll(){
        return users
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public List<User> listByEmail(String email){
        return users
                .values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .collect(Collectors.toList());
    }
}
