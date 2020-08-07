package com.tatto.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "style")
public class Style {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "style")
    private String style;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "styles", cascade = CascadeType.ALL)
    private List<Tattoo> tattoos = new ArrayList<>();

    public Style(Long id, String style) {
        this.id = id;
        this.style = style;
    }
}
