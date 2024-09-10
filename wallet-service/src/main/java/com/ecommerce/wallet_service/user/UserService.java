package com.ecommerce.wallet_service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public List<User> getUsers(){
        return repository.findAll();
    }

    public User registerUser(User user){
        User registeredUser = new User(
            user.getEmail(),
            this.passwordEncoder.encode(user.getPassword()),
            user.getEmail()
        );
        
        return repository.save(registeredUser);
    }

    public LoginResponse loginUser(UserLogin user){

        User checkedUser = repository.findByEmail(user.getEmail());

        if (checkedUser != null){
            String password = user.getPassword();
            String encodedPass = checkedUser.getPassword();
            Boolean isPassRight = passwordEncoder.matches(password, encodedPass);

            if (isPassRight){
                return new LoginResponse("Login success", true);
            }
            else{
                return new LoginResponse("password failed", false);
            }
        }else{
            return new LoginResponse("Email doesn't Exist", false);
        }

    }
}
