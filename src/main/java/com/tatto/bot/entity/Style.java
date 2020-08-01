package com.tatto.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tatto.bot.entity.enums.EStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "style")
    @Enumerated(EnumType.STRING)
    private EStyle style;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "styles", cascade = CascadeType.ALL)
    private List<Tattoo> tattoos = new ArrayList<>();
}
