package com.travel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
public class Plan  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String url;

    private Date startTime;

    private int numberOfSlot;

    private int numberOfReservedSlot;

    private double adultPrice;

    private double childPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plans_places",
            joinColumns = @JoinColumn(name = "plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "place_id", referencedColumnName = "id")
    )
    private Set<Place> places = new HashSet();

    @OneToMany(mappedBy = "plan")
    private Set<Booking> bookings = new HashSet();

}
