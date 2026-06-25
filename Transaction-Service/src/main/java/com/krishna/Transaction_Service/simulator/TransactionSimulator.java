package com.krishna.Transaction_Service.simulator;

import java.util.List;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.krishna.Transaction_Service.dto.CreateTransactionRequest;
import com.krishna.Transaction_Service.dto.UserProfile;
import com.krishna.Transaction_Service.service.TransactionService;

@Component
public class TransactionSimulator {

    private final TransactionService transactionService;

    private final UserProfileStore store;
   

    public TransactionSimulator(TransactionService transactionService, UserProfileStore store) {
		super();
		this.transactionService = transactionService;
		this.store = store;
	}

	private final Random random = new Random();
    
    

    @Scheduled(
            fixedRateString =
                    "${simulation.fixed-rate-ms}"
    )
    public void generateTransaction() {

        UserProfile profile =
                randomProfile();

        boolean fraud =
                random.nextInt(100) < 10;

        CreateTransactionRequest request =
                fraud
                        ? generateFraud(profile)
                        : generateNormal(profile);

        transactionService.createTransaction(request);
    }

    private UserProfile randomProfile() {

        List<UserProfile> profiles =
                store.getProfiles();

        return profiles.get(
                random.nextInt(profiles.size())
        );
    }

    private CreateTransactionRequest generateNormal(
            UserProfile profile) {

        double amount =
                profile.getAverageAmount()
                        * (0.8 + random.nextDouble());

        String device =
                profile.getDevices()
                        .get(
                                random.nextInt(
                                        profile.getDevices().size()
                                )
                        );

        return new CreateTransactionRequest.Builder()
                .userId(profile.getUserId())
                .amount(amount)
                .country(profile.getCountry())
                .deviceId(device)
                .build();
    }

    private CreateTransactionRequest generateFraud(
            UserProfile profile) {

        return CreateTransactionRequest.builder()
                .userId(profile.getUserId())
                .amount(50000.0)
                .country("USA")
                .deviceId("UNKNOWN_DEVICE")
                .build();
    }
}
