package com.example.usermgmt.service;
import com.example.usermgmt.dto.UserRequest;
import com.example.usermgmt.exception.ConflictException;
import com.example.usermgmt.exception.NotFoundException;
import com.example.usermgmt.model.User;
import com.example.usermgmt.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public List<User> getAllUsers() { return userRepository.findAll(); }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id=" + id + " not found"));
    }
    public User createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) throw new ConflictException("User with email=" + request.getEmail() + " already exists");
        User user = new User();
        apply(user, request);
        return userRepository.save(user);
    }
    public User updateUser(Long id, UserRequest request) {
        User user = getUserById(id);
        if (userRepository.existsByEmailAndIdNot(request.getEmail(), id)) throw new ConflictException("Another user with email=" + request.getEmail() + " already exists");
        apply(user, request);
        return userRepository.save(user);
    }
    public void deleteUser(Long id) { userRepository.delete(getUserById(id)); }
    private void apply(User user, UserRequest request) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setStatus(request.getStatus() == null || request.getStatus().isBlank() ? "ACTIVE" : request.getStatus().toUpperCase());
    }
}
