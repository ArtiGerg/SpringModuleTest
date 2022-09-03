package app.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Officer implements UserDetails {

    @Id
    @GeneratedValue
    private long Id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDateTime workStart;

    private LocalDateTime workEnd;

    public Officer() {}

    public Officer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Officer(long id, String username, String password, LocalDateTime workStart, LocalDateTime workEnd) {
        Id = id;
        this.username = username;
        this.password = password;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Officer(String username, String password, LocalDateTime workStart, LocalDateTime workEnd) {
        this.username = username;
        this.password = password;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public boolean isEnable() {
        return workEnd == null;
    }

    private GrantedAuthority createOneAuthority() {
        return new SimpleGrantedAuthority("ADMIN");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
    }
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDateTime workStart) {
        this.workStart = workStart;
    }

    public LocalDateTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalDateTime workEnd) {
        this.workEnd = workEnd;
    }
}
