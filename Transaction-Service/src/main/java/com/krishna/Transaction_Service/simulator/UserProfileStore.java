package com.krishna.Transaction_Service.simulator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.krishna.Transaction_Service.dto.UserProfile;

@Component
public class UserProfileStore {

    public List<UserProfile> getProfiles() {

        return List.of(

                new UserProfile(
                        "U100",
                        "India",
                        List.of("D1", "D2"),
                        1500.0
                ),

                new UserProfile(
                        "U101",
                        "India",
                        List.of("D3"),
                        2500.0
                ),

                new UserProfile(
                        "U102",
                        "India",
                        List.of("D4", "D5"),
                        3500.0
                )
        );
    }
}