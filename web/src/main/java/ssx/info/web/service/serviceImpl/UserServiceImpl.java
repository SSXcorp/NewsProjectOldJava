package ssx.info.web.service.serviceImpl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ssx.info.web.exception.NullEntityReferenceException;
import ssx.info.web.model.User;
import ssx.info.web.repository.UserRepository;
import ssx.info.web.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (Objects.isNull(user)) {
            throw new NullEntityReferenceException("Something went wrong. Try to create user one more time");
        }
        return userRepository.save(user);
    }


    @Override
    public User update(User user) {
        if (Objects.isNull(user)) {
            throw new NullEntityReferenceException("No user found, try again.");
        }
        User oldUser = readById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    public User readById(long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("There is no user with id " + id);
        }
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

}
