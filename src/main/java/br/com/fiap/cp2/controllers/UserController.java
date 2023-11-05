package br.com.fiap.cp2.controllers;

import br.com.fiap.cp2.dtos.UserDto;
import br.com.fiap.cp2.models.UserModel;
import br.com.fiap.cp2.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController extends GenericController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
        Optional<UserModel> optional = userService.findById(id);
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody UserDto userDto, BindingResult result) {
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.addUser(userDto));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<UserModel> optional = userService.findById(id);
        return optional.map(user -> {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult result) {
        Optional<UserModel> optional = userService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.putUser(userDto, id));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }


}
