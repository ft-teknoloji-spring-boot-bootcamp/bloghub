package service;

import model.User;
import model.enums.StatusType;
import repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String email, String password) {

        User user = new User(email, password); // ödev password' hash'le

        userRepository.save(user);

        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("user bulunamadı"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void changeStatus(String email, StatusType statusType) {
        getUserByEmail(email).setStatusType(statusType);
    }

    public void changeStatus(List<String> emailList, StatusType statusType) {

        Map<String, User> allUsersMap = getAllUsersMap();

        emailList.forEach(email -> {

            User user = allUsersMap.get(email);
            user.setStatusType(statusType);
        });

    }

    public void changeStatus2(List<String> emailList, StatusType statusType) {
        emailList.forEach(email -> changeStatus(email, statusType));
    }

    public Map<String, User> getAllUsersMap() {
        return getAllUsers().stream()
                .collect(Collectors.toMap(User::getEmail, Function.identity()));
    }
}
