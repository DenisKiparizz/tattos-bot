package com.tatto.bot.entity.prod;

import javax.persistence.*;

@Entity
@Table(name = "tattoo")
public class Tattoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "style")
    @Enumerated(EnumType.STRING)
    private EStyle style;

    @Column(name = "description")
    private String description;

    public Tattoo() {
    }

    public EStyle getStyle() {
        return style;
    }

    public void setStyle(EStyle style) {
        this.style = style;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
