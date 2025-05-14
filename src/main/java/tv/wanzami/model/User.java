package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import tv.wanzami.enums.Role;

/**
 * User Model
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;
	
	@Column(name = "first_name", nullable = false, length = 200)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 200)
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "telephone", nullable = false, unique = true)
	private String telephone;
	
	@Enumerated(EnumType.STRING)
    private Role role = Role.NORMAL; // default value;

	@Column(name = "created_at", nullable = true)
	private Instant created_at;

	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
//		Never want to return the password
		return "###--Nice Try--###";
	}
	
	public String getSecret() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User(Long userId) {
		this.id = userId;
	}

	public User() {
	}

	public User(String firstName, String lastName, String email, String passoword, String telephone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = 0;
		this.email = email;
		this.password = passoword;
		this.telephone = telephone;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(String role) {
		this.role = Role.valueOf(role);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
