package example.service;
import example.model.User;
import example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) throws ServiceException{
        return userRepository.save(user);
    }
    public User removeUser(User deletedUser){
        if(userRepository.existsById(deletedUser.getId())) {
           userRepository.delete(deletedUser);
        }
        return deletedUser;
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }




}
