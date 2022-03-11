package com.example.freshfoods.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "producer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String contactNo;

    @OneToMany(
            targetEntity = Food.class,
            mappedBy = "producer",
            fetch = FetchType.LAZY
    )
    private List<Food> foods;
}
