package pl.dma.appka.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dma.appka.dto.RegisterFormDto;
import pl.dma.appka.role.Role;
import pl.dma.appka.role.RoleRepository;

@Component
public class DtoToUserConverter {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DtoToUserConverter(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User convertDtoToUser(RegisterFormDto dto) {
        Role role = roleRepository.findById(2).orElseThrow(RuntimeException::new);
        User user = User.builder().email(dto.getEmail()).name(dto.getLogin()).password(passwordEncoder.encode(dto.getPassword())).build();
        user.addRole(role);
        return user;
    }

}
