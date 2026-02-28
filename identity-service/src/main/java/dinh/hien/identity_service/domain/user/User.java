package dinh.hien.identity_service.domain.user;

import dinh.hien.identity_service.domain.exception.DError;
import dinh.hien.identity_service.domain.exception.DomainException;
import dinh.hien.identity_service.domain.role.Role;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UserId id;
    private String username;
    private Password password; // value object
    private Email email;
    private Role role;

    public void authenticatePassword(String rawPassword,PasswordHasher passwordHasher){
       if(!passwordHasher.matches(rawPassword,password.getHashValue())){
           throw new DomainException(DError.PASSWORD_INVALID);
       }
    }
}
