package com.infosupport.utjava.mockito;

public class PasswordService {

    private final UserRepo repo;

    public PasswordService(UserRepo repo) {
        this.repo = repo;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = repo.find(username);
        if (user != null) {
            if (user.password().equals(oldPassword)) {
                User update = repo.update(username, newPassword);
                return true;
            }
        }
        return false;
    }
}
