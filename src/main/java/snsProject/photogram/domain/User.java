package snsProject.photogram.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 20)
    private String username;
    private String password;

    private String name;
    private String website;
    private String bio;

    private String email;

    private String phone;
    private String gender;

    private String profileImageUrl;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
