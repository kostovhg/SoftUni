package AutoMappingExerciseApplication.controllers;

import AutoMappingExerciseApplication.models.entities.Game;
import AutoMappingExerciseApplication.models.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserSession {

    private User currentUser;
    private boolean isCurrentUserAdmin;
    private boolean isCurrentUserLoggedIn;
    private Set<Game> shoppingCard;

    public UserSession() {
        this.shoppingCard = new HashSet<>();
    }

    public void login(User user) {
        this.setCurrentUser(user);
        this.setCurrentUserLoggedIn(true);
        this.setCurrentUserAdmin(user.isAdmin());
    }

    public void logout() {
        this.setCurrentUser(null);
        this.setCurrentUserLoggedIn(false);
        this.setCurrentUserAdmin(false);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isCurrentUserAdmin() {
        return this.isCurrentUserLoggedIn() && this.currentUser.isAdmin();
    }

    public void setCurrentUserAdmin(boolean currentUserAdmin) {
        this.isCurrentUserAdmin = currentUserAdmin;
    }

    public boolean isCurrentUserLoggedIn() {
        return this.isCurrentUserLoggedIn;
    }

    public void setCurrentUserLoggedIn(boolean currentUserLoggedIn) {
        this.isCurrentUserLoggedIn = currentUserLoggedIn;
    }

    public boolean addItemToShoppingCard(Game game) {
        return this.shoppingCard.add(game);
    }

    public boolean removeItemFromShoppingCard(Game game) {
        return this.shoppingCard.remove(game);
    }

    public void clearShoppingCard() {
        this.shoppingCard.clear();
    }

    public java.util.Set<Game> getShoppingCard() {
        return this.shoppingCard;
    }
}
