package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.exception.InvalidProfileException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private static final Logger log = Logger.getLogger(ProfileService.class);

    public void validate(Profile profile) {
        if (profile == null) {
            log.error("empty profile");
            throw new InvalidProfileException();
        }

        String name = profile.getName();
        if (name == null || name.isEmpty() || name.length() > 50) {
            log.error("empty or too large profile name");
            throw new InvalidProfileException();
        }
    }
}