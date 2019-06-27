package com.travel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "places")
@Getter
@Setter
@NoArgsConstructor
public class Place  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    private String url;

    private String imageUrl;

    private Long parentId;

    @ManyToMany(mappedBy = "places", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Plan> plans = new HashSet();

}
