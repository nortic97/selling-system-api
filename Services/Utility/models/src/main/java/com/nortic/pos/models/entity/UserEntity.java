package com.nortic.pos.models.entity;

import com.nortic.pos.models.enums.AccountTypeEnum;
import com.nortic.pos.models.enums.DocumentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private BranchEntity branch;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentType;

    @Column(unique = true, nullable = false)
    private String documentNumber;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    private Boolean isActive;

    private Boolean isPrincipalAdmin;

    private String address;

    @Column(nullable = false)
    private LocalDate birthDate;

    private String photo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.isActive = Boolean.FALSE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.deletedAt = LocalDateTime.now();
        this.isActive = Boolean.FALSE;
    }

    public void changeStatus(Boolean status) {
        if (Boolean.TRUE.equals(status)) {
            this.isActive = Boolean.TRUE;
            this.deletedAt = null;
        } else {
            this.isActive = Boolean.FALSE;
        }
    }
}
