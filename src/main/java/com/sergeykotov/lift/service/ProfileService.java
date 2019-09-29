package com.sergeykotov.lift.service;

import com.sergeykotov.lift.domain.Profile;
import com.sergeykotov.lift.exception.InvalidProfileException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private static final Logger log = Logger.getLogger(ProfileService.class);
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_NOTE_LENGTH = 4000;

    public void validate(Profile profile) {
        if (profile == null) {
            log.error("empty profile");
            throw new InvalidProfileException();
        }

        String name = profile.getName();
        if (name == null || name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            log.error("empty or too large profile name");
            throw new InvalidProfileException();
        }

        String note = profile.getNote();
        if (note != null && note.length() > MAX_NOTE_LENGTH) {
            log.error("too large profile note");
            throw new InvalidProfileException();
        }
    }
}