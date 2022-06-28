package com.pijjapril.host.domain;

import com.pijjapril.util.domain.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "hosts")
public class Client extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "ip", unique = true)
    private String ip;
}
