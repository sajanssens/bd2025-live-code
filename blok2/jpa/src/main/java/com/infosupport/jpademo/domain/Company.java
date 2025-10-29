package com.infosupport.jpademo.domain;

import com.infosupport.jpademo.dao.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity
public class Company implements HasId {

    @Id // @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
}
