package br.com.fiap.cp2.repositories;

import br.com.fiap.cp2.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserDetails findByusername(String username);

}
