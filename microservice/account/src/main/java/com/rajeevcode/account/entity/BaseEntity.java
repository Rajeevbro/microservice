package com.rajeevcode.account.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    private LocalDateTime created;

    private LocalDateTime updated;


    private String createdBy;

    private String updatedBy;

}
