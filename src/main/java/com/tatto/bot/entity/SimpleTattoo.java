package com.tatto.bot.entity;

import javax.persistence.*;

//@Entity
//@Table(name = "tattoo")
public class SimpleTattoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "style")
    private String style;
    @Column(name = "description")
    private String description;

    public SimpleTattoo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
