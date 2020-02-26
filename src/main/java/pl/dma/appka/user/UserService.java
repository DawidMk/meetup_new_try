package pl.dma.appka.user;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.dma.appka.dto.RegisterFormDto;
import pl.dma.appka.exceptions.UserDinnaeExistsException;
import pl.dma.appka.exceptions.UserExistsException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
    private final DtoToUserConverter dtoToUserConverter;

    public UserService(UserDao userDao, DtoToUserConverter dtoToUserConverter) {
        this.userDao = userDao;
        this.dtoToUserConverter = dtoToUserConverter;
    }

    public void saveUserToDb(RegisterFormDto registerFormDto) {
        if (userDao.findUserInDb(registerFormDto.getEmail()).isEmpty()) {
            userDao.saveUserToDb(dtoToUserConverter.convertDtoToUser(registerFormDto));
        } else {
            throw new UserExistsException("użytkownik istnieje");
        }
    }

    public String getLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    public Integer getLoggedUserId(){
        String loggedUserName = getLoggedUserName();

        return userDao.findUserInDb(loggedUserName).stream().findFirst().orElseThrow(() -> new UserDinnaeExistsException("eine kleine neine useren")).getId();
    }

    public User getLoggedUser(){
        String loggedUserName = getLoggedUserName();

        return userDao.findUserInDb(loggedUserName).stream().findFirst().orElseThrow(() -> new UserDinnaeExistsException("eine kleine neine useren"));
    }
}
