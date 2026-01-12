package org.nestech.identityprovider.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nestech.identityprovider.user.mappers.UserMapper;
import org.nestech.identityprovider.user.model.Role;
import org.nestech.identityprovider.user.model.User;
import org.nestech.identityprovider.user.repository.UserRepository;
import org.nestech.identityprovider.user.request.ResetPassRequest;
import org.nestech.identityprovider.user.request.UserRequest;
import org.nestech.identityprovider.user.responses.UserResponse;
import org.nestech.identityprovider.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<Object> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "10") int limit,
                                           @RequestParam(value = "sort", defaultValue = "id") String sort,
                                           @RequestParam(value = "dir", defaultValue = "desc") String dir,
                                           @RequestParam(value = "status", required = false) Integer status){
        return userService.getUsers(page, limit, sort, dir, status);

    }

    @GetMapping("/searchUsers")
    public ResponseEntity<List> searchUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit,
                                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                                            @RequestParam(value = "dir", defaultValue = "desc") String dir,
                                            @RequestParam(value = "search", required = false) String search){
        return userService.getUser(page, limit, sort, dir,search);
    }

    @GetMapping("/listUsers")
    public PagedModel<UserResponse> listUsers(Pageable pageable,
                                              @RequestParam(value = "status", required = false) Boolean status,
                                              @RequestParam(value = "email", required = false) String email,
                                              @RequestParam(value = "role", required = false) String role){

        return pagedResourcesAssembler.toModel(userService.getUser(pageable,email, role, status));
    }


    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId){
        return userService.getUser(Integer.parseInt(userId));
    }


    @PostMapping(value = "/block")
    public ResponseEntity<UserResponse> lock(@RequestBody UserRequest userRequest){
        return userService.blockUser(userRequest);
    }

    @PostMapping(value = "/changePassword")
    public ResponseEntity<Object> reset(@Valid @RequestBody ResetPassRequest request){
        return userService.resetPassword(request);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidTopTalentDataException(MethodArgumentNotValidException validException) {
        return ErrorResponse
                .create(new Exception(),
                        HttpStatus.BAD_REQUEST,
                        String.valueOf(Objects.requireNonNull(validException.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map((s)-> s.getField()+": "+s.getDefaultMessage())
                                .reduce((s1, s2)-> s1+" , "+s2))));
    }

}
