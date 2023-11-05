package br.com.fiap.cp2.services;

import br.com.fiap.cp2.dtos.UserDto;
import br.com.fiap.cp2.models.UserModel;
import br.com.fiap.cp2.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends EntityService<UserModel> {

    private final UserRepository userRepository;

    public UserService(JpaRepository<UserModel, Long> repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    public UserModel addUser(UserDto userDto){
        try {
            UserModel user = new UserModel();
            BeanUtils.copyProperties(userDto, user);
            return repository.save(user);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserModel putUser(UserDto userDto, Long id){
        try {
            Optional<UserModel> userOptional = userRepository.findById(id);
            UserModel user = userOptional.get();
            BeanUtils.copyProperties(userDto, user);
            return repository.save(user);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
