package com.travel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
@Getter
@Setter
@NoArgsConstructor
public class Tour  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String url;

    private String imageUrl;

    private String fileContentUrl;

    @Column(columnDefinition = "text")
    private String description;

    private int numberOfDate;

    private int numberOfNight;

    @OneToMany(mappedBy = "tour")
    private Set<Plan> plans = new HashSet<>();

    private boolean enable = true;

}