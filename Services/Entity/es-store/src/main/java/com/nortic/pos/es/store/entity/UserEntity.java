package com.nortic.pos.es.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "\"user\"")
@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private UUID id;
}
