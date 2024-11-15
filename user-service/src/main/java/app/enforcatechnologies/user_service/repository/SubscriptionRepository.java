package app.enforcatechnologies.user_service.repository;

import app.enforcatechnologies.user_service.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


}
