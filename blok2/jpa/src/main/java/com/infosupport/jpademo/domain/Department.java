package com.infosupport.jpademo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Department {

    @Id // @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "worksAt")
    private Set<Person> workers;
}
