package pl.dma.appka.picture;


import lombok.Getter;
import lombok.Setter;
import pl.dma.appka.event.Event;

import javax.persistence.*;

@Entity
@Table(name="pictures")
@Getter
@Setter
public class Picture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;


    @ManyToOne
    private Event owningEvent;

    @Lob
    private byte[] data;

    public Picture() {
    }

    public Picture(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }
}
