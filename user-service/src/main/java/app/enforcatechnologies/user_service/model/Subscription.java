package app.enforcatechnologies.user_service.model;

import app.enforcatechnologies.user_service.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String talentName;
    private String email;

    private LocalDate creationDate;
    private LocalDate activationDate;
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private Status status;

}

