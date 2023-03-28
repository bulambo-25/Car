package mc.tech.com.service.domain;

import lombok.extern.slf4j.Slf4j;
import mc.tech.com.domain.User;

import mc.tech.com.repository.UserRepository;
import mc.tech.com.service.imp.userImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService implements userImplementation{

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {

        User user1=this.userRepository.save(user) ;
        log.info("user was saved ::",user1);
        return user1;
    }

    @Override
    public Optional<User> read(Long aLong) {
        Optional<User>optionalUser=this.userRepository.findById(aLong);
        log.info("read user by Id::",optionalUser);
        return optionalUser;
    }



    @Override
    public User getUserByEmail(String email) {
        User user=this.userRepository.findByEmail(email);
        log.info("find  user by email::",user);
        return user;
    }


    @Override
    public void delete(Long Id) {

        this.userRepository.deleteById(Id);

    }
}