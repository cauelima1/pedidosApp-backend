package pedidosApp.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BlackListedToken {

    @Id
    private String token;
    private long expirationMillis;
    private LocalDateTime blacklistedAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpirationMillis() {
        return expirationMillis;
    }

    public void setExpirationMillis(long expirationMillis) {
        this.expirationMillis = expirationMillis;
    }

    public LocalDateTime getBlacklistedAt() {
        return blacklistedAt;
    }

    public void setBlacklistedAt(LocalDateTime blacklistedAt) {
        this.blacklistedAt = blacklistedAt;
    }
}
