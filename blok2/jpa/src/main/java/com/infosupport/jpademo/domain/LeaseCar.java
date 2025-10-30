package com.infosupport.jpademo.domain;

import com.infosupport.jpademo.dao.HasId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class LeaseCar implements HasId {

    @Id // @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String brand;

    @OneToOne(/*mappedBy = "leaseCar", */cascade = CascadeType.MERGE)
    @MapsId // this FK is also the PK; we need to disable @GeneratedValue on the @Id
    @JoinColumn(name = "person_id") // we can rename the foreign key column
    private Person owner;
}
