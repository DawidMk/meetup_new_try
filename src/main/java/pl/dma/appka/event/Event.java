package pl.dma.appka.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.dma.appka.comment.Comment;
import pl.dma.appka.picture.Picture;
import pl.dma.appka.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "event_name")
    String eventName;

    @Column
    String description;

    @Column
    LocalDate startDate;

    @Column
    LocalDate endDate;

//    @OneToMany(mappedBy = "owningEvent")
    @OneToOne
    Picture eventPicture;

    @ManyToOne
    @JoinColumn(name = "owner_id")
//    @JsonManagedReference
    @JsonIgnore
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "users_events",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
//    @JsonManagedReference
    @JsonIgnore
    private Set<User> attendants;

    @OneToMany(mappedBy = "commentedEvent")
//    @JsonManagedReference
    @JsonIgnore
    private Set<Comment> comments;

    public void addUserToEvent(User user) {
        attendants.add(user);
    }

    public void removeUserFromEvent(User user) {
        attendants.remove(user);
    }
}
