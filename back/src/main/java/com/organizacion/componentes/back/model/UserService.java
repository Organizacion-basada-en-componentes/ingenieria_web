package com.organizacion.componentes.back.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    RepositoryUser repositoryUser;

    public List<User> getAllUsers() {
        return repositoryUser.findAll();
    }

    public User getUser(Long id) {
        return repositoryUser.getReferenceById(id);
    }

    public User addUser(User u) {
        return repositoryUser.saveAndFlush(u);
    }

    public void updateUser(User u) {
        User user = repositoryUser.getReferenceById(u.getId());
        user.setPassword(u.getPassword());
        user.setRole(u.getRole());
        user.setEmail(u.getEmail());
        repositoryUser.save(user);
    }

    public void removeUser(User u) {
        repositoryUser.delete(u);
    }

    public void removeUserID(Long id) {
        repositoryUser.deleteById(id);
    }
}
