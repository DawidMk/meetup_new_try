package pl.dma.appka.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.dma.appka.comment.Comment;
import pl.dma.appka.event.Event;
import pl.dma.appka.role.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(mappedBy = "attendants")
    @JsonBackReference
    private Set<Event> attendedEvents;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonBackReference
    private Set<Role> roles;

    @OneToMany(mappedBy = "owner")
    @JsonBackReference
    private Set<Event> events;

    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private Set<Comment> comments;

    public void addRole(Role role) {
        roles = initializeRoles();
        roles.add(role);
        role.add(this);
    }

    private Set<Role> initializeRoles() {
        return new HashSet<>();
    }

    public User() {
    }
}
