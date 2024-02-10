package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.DTO.UserDTO.UpdateUserStatus;
import com.tool.RecruitXpert.DTO.UserDTO.UserRequest;
import com.tool.RecruitXpert.DTO.UserDTO.UserResponse;
import com.tool.RecruitXpert.Entities.User;
import com.tool.RecruitXpert.Exceptions.UserNotFoundException;
import com.tool.RecruitXpert.Repository.UserRepository;
import com.tool.RecruitXpert.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserResponse addUser(UserRequest userRequest) {
        User user = UserTransformer.UserRequestToUser(userRequest);
        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponse(savedUser);
    }

    public String deleteUser(int id) {
            Optional<User> optionalUser = userRepository.findById(id);
            if(!optionalUser.isPresent()){
                throw new UserNotFoundException("User not Found");
            }
            userRepository.deleteById(id);
            return "User Successfully Deleted";
    }

    public String updateUserStatus(UpdateUserStatus updateUserStatus) {
        Optional<User> optionalUser = userRepository.findById(updateUserStatus.getId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not Found");
        }
           User user = new User();
           user.setStatus(updateUserStatus.getStatus());
           userRepository.save(user);
           return "Status Updated Succesfully";
    }

    public String updateUser(UserRequest userRequest) {
        User user = UserTransformer.UserRequestToUser(userRequest);
        userRepository.save(user);
        return "Updated Successfully !!";

    }
}
