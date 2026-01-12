package org.nestech.identityprovider.user.service;

import org.nestech.identityprovider.user.mappers.UserMapper;
import org.nestech.identityprovider.user.mappers.UserMapperImpl;
import org.nestech.identityprovider.user.model.Role;
import org.nestech.identityprovider.user.repository.SearchCriteria;
import org.nestech.identityprovider.user.repository.UserRepository;
import org.nestech.identityprovider.user.model.User;
import org.nestech.identityprovider.user.repository.UserSpecification;
import org.nestech.identityprovider.user.request.ResetPassRequest;
import org.nestech.identityprovider.user.request.UserRequest;
import org.nestech.identityprovider.user.responses.ResponseHandler;
import org.nestech.identityprovider.user.responses.UserResponse;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<UserResponse> blockUser(UserRequest userRequest){
        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());
        ResponseEntity<UserResponse> response;
        if(userOptional.isPresent()){
            User user = userOptional.get();
            userRepository.setLocked(userRequest.isLocked(), user.getId());
            UserResponse userResponse = new UserResponse(user.getId(),
                    user.getEmail(),
                    user.getRole(),
                    userRequest.isLocked());
            response = new ResponseEntity<>(userResponse, HttpStatus.OK);
        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    public ResponseEntity<UserResponse> getUser(Integer userId){
        final Optional<User> userOptional = userRepository.findById(userId);
        ResponseEntity<UserResponse> response;
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getRole(), user.isLocked());
            response = new ResponseEntity<>(userResponse, HttpStatus.OK);
        }else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    public ResponseEntity<List> getUser(int page, int limit, String sort,String dir,String search){
        final PageRequest pageRequest = PageRequest.of(page, limit, Sort.Direction.fromString(dir), sort);
        final Page<User> users = userRepository.findAllByEmailContaining(search, pageRequest);

        List<UserResponse> list = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getRole(), user.isLocked());
            list.add(userResponse);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<Object> getUsers(int page, int limit, String sort, String dir, Integer status) {

        final PageRequest pageRequest = PageRequest.of(page, limit, Sort.Direction.fromString(dir), sort);
        final Page<User> users;
        if(status != null){
            boolean locked = status != 0;
            users = userRepository.findByLocked(locked, pageRequest);
        }else {
            users = userRepository.findAll(pageRequest);
        }

        List<UserResponse> list = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getRole(), user.isLocked());
            userResponse.add(Link.of("/api/v1/users/"+userResponse.getId()).withSelfRel().withType("GET"));
            list.add(userResponse);
        }
        Page<UserResponse> responsePage = new PageImpl<>(list);
        return ResponseHandler.generateResponse(users.getTotalPages()+" pages found", HttpStatus.OK, responsePage);
    }

    public ResponseEntity<Object> resetPassword(ResetPassRequest request) {

        if(request != null){
            final String oldPassword = request.getOldPassword();
            final Optional<User> byId = userRepository.findById(request.getId());
            User user = null;
            if(byId.isPresent()){
                user = byId.get();
            }
            if(user != null && oldPassword != null & passwordEncoder.matches(oldPassword, user.getPassword())){
                userRepository.updatePasswordById(passwordEncoder.encode(request.getNewPassword()), request.getId());
                return ResponseHandler.generateResponse("Password changed successfully", HttpStatus.OK, null);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    public Page<UserResponse> getUser(Pageable pageable,String email, String role, Boolean status){
        UserMapper userMapper = new UserMapperImpl();
        Role enumRole;
        UserSpecification specification1 = new UserSpecification(new SearchCriteria("email", ":", email));
        Specification<User> specification = specification1;

        if (Objects.nonNull(role)) {
            try {
                enumRole = Role.valueOf(role);
                UserSpecification specification2 = new UserSpecification(new SearchCriteria("role", ":", enumRole));
                specification = specification1.and(specification2);
            }catch (Exception ignored){}
        }

        if(Objects.nonNull(status)){
            UserSpecification specification3 = new UserSpecification(new SearchCriteria("locked", ":", status));
            specification = specification.and(specification3);
        }

        final Page<User> all = userRepository.findAll(specification, pageable);
        final Page<UserResponse> map = all.map(userMapper::domainToResponse);
        return map;
    }

}
