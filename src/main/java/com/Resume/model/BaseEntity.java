package com.Resume.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass // MappedSuperclass - це супер клас для якогої не потрібно створювати таблицю
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Column(name = "created_date")
    private Date create;

    @LastModifiedDate
    @Column(name = "update_date")
    private Date update;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", create=" + create +
                ", update=" + update +
                ", status=" + status +
                '}';
    }
}
