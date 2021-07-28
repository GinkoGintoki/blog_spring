package ginko.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "public_name")
    private String publicName;
}
