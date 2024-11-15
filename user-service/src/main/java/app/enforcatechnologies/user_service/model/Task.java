package app.enforcatechnologies.user_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private String description;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "jobTalent_id")
    private JobTalent jobTalent;
}
