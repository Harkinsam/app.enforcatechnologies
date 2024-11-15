package app.enforcatechnologies.user_service.service;

import app.enforcatechnologies.user_service.enums.Status;
import app.enforcatechnologies.user_service.model.Subscription;
import app.enforcatechnologies.user_service.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription addSubscription(Subscription subscription) {
        subscription.setCreationDate(LocalDate.now());

        // Set the activation date - If it's not provided, use the current date
        if (subscription.getActivationDate() == null) {
            subscription.setActivationDate(LocalDate.now()); // Set to current date if not provided
        }
        // Set the expiry date - If it's not provided,I set it to 1 year after the activation date
        if (subscription.getExpiryDate() == null) {
            subscription.setExpiryDate(subscription.getActivationDate().plusYears(1)); // Default: 1 year from activation date
        }
        if (subscription.getStatus() == null) {
            subscription.setStatus(Status.CREATED);
        }
        return subscriptionRepository.save(subscription);
    }


    public Subscription activateSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subscription not found"));

        subscription.setActivationDate(LocalDate.now());
        subscription.setExpiryDate(LocalDate.now().plusMonths(1)); // Assuming 1 month subscription duration
        subscription.setStatus(Status.ACTIVE);
        return subscriptionRepository.save(subscription);
    }

    public Page<Subscription> getAllSubscriptions(int pageNo, int pageSize) {
        return subscriptionRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

}