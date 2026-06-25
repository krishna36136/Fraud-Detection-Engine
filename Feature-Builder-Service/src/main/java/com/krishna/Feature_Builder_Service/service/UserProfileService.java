package com.krishna.Feature_Builder_Service.service;

import java.util.HashSet;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

import com.krishna.Feature_Builder_Service.model.UserProfile;
import com.krishna.Feature_Builder_Service.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfile getOrCreate(String userId) {

        UserProfile profile =
                repository.get(userId);

        if(profile != null)
            return profile;

        profile =
                UserProfile.builder()
                           .userId(userId)
                           .avgAmount(0.0)
                           .knownDevices(new HashSet<>())
                           .transactionTimes(
                               new LinkedList<>()
                           )
                           .build();

        repository.save(profile);

        return profile;
    }

    public void save(UserProfile profile) {

        repository.save(profile);
    }
}