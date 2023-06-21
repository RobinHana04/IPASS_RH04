package org.backend.security;

import java.util.List;

public class Username {
    public String username;

    public List<Username> allUsernames;

    public Username(String username) {
        this.username = username;
    }

    public void setAllUsernames(List<Username> allUsernames) {
        this.allUsernames = allUsernames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Username> getAllUsernames() {
        return allUsernames;
    }

    public synchronized void addUsername(Username un) {
        if (!getAllUsernames().contains(un)) {
            getAllUsernames().add(un);
        }
    }

    @Override
    public String toString() {
        return "Username{" +
                "username='" + username + '\'' +
                '}';
    }
}
