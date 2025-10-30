package com.infosupport.jpademo.domain;

import com.infosupport.jpademo.dao.HasId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Department implements HasId {

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "worksAt", cascade = CascadeType.MERGE)
    @Builder.Default @EqualsAndHashCode.Exclude @ToString.Exclude
    private Set<Person> workers = new HashSet<>();

    // FIX voor terugverwijzing
    public void addWorker(Person worker) {
        this.workers.add(worker);
        worker.setWorksAt(this);
    }

    // AFBLIJVEN!!!!
    // private Set<Person> getWorkers() {
    //     return workers;
    // }
}
