package com.tobeto.java4a.pair4lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "phone")
	private String phone;
	
	private String email;
	
	private String password;
	
//	@JoinTable(name="roles", joinColumns = @JoinColumn(name="user_id"))
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private List<Role> authorities;

	@OneToMany(mappedBy = "user")
	private List<Borrowing> borrowings;

	@Override
    public Collection<Role> getAuthorities() {
        return authorities;
    }
	
	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
