package com.materials.util;

import com.materials.model.User;

public class SessionManager {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static String getCurrentUsername() {
        return currentUser != null ? currentUser.getUsername() : "Unknown";
    }

    public static boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == User.UserRole.ROLE_ADMIN;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
