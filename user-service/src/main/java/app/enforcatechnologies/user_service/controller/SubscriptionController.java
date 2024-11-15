package app.enforcatechnologies.user_service.controller;

import app.enforcatechnologies.user_service.model.Subscription;
import app.enforcatechnologies.user_service.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // Create a new subscription
    @PostMapping("/add")
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription createdSubscription = subscriptionService.addSubscription(subscription);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }



    // Get all subscriptions with pagination
    @GetMapping("/pages")
    public ResponseEntity<Page<Subscription>> getAllSubscriptions(
            @RequestParam(defaultValue = "1") int pageNo, // Default to page 1
            @RequestParam(defaultValue = "5") int pageSize) { // Default to page size 5

        int zeroBasedPageNumber = pageNo - 1;

        Page<Subscription> subscriptions = subscriptionService.getAllSubscriptions(zeroBasedPageNumber, pageSize);

        if (subscriptions.hasContent()) {
            return ResponseEntity.ok(subscriptions);
        } else {
            return ResponseEntity.ok(Page.empty(subscriptions.getPageable()));
        }
    }


}
