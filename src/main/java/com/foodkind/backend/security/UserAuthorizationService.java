package com.foodkind.backend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Provides authorization checks used in SpEL expressions
 * within @PreAuthorize annotations.
 */
@Slf4j
@Component("userAuthorizationService")
public class UserAuthorizationService {

    /**
     * Check if the authenticated user owns the resource with the given userId.
     */
    public boolean isOwner(Authentication authentication, String userId) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return false;
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        boolean isOwner = userDetails.getId().equals(userId);
        log.debug("Authorization check — User {} {} resource for user {}",
                userDetails.getId(), isOwner ? "owns" : "does NOT own", userId);
        return isOwner;
    }
}
