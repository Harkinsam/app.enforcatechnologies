package app.enforcatechnologies.user_service.model;

import app.enforcatechnologies.user_service.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String fileUrl;  // URL to the resume file
    private String applicantName;
    private String applicantEmail;
    private String position;
    private String company;
    private LocalDate applicationDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    // Getters and setters
}
