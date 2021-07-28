package ginko.blog.entity;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<Rate> rates;

    @Column(name = "is_published")
    private Boolean isPublished;

    private String headline;

    private String content;

    @Column(name = "writing_date")
    private LocalDateTime writingDate;

    @Column(name = "publish_date")
    private LocalDate publishDate;
}
