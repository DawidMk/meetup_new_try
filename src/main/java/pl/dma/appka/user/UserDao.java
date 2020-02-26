package pl.dma.appka.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Getter
@Setter
@Repository
public class UserDao {

    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUserToDb(User user){
        userRepository.save(user);
    }

//    public List<User> findUserInDb(String email){
//        return userRepository.findByEmail(email);
//    }

    public List<User> findUserInDb(String name){
        return userRepository.findByName(name);
    }
}
