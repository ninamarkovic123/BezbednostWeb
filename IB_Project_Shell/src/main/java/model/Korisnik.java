package model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Users")
public class Korisnik implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9053216854627868859L;

	/**
	 * 
	 */
	

	/**
	 * 
	 */
	

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "certificate")
    private String certificate;
    
    @Column(name = "active")
    private Boolean active;
    
    @Column(name = "last_password_reset")
    private Timestamp lastPasswordReset;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Autorizacija> authorities;

    public Korisnik() {

    }

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
		return password;
	}

	 public void setPassword(String password) {
	        Timestamp now = new Timestamp(new Date().getTime());
	        this.setLastPasswordReset(now);
	        this.password = password;
	    }

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setAuthorities(List<Autorizacija> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    public Timestamp getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Timestamp lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }
    
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @JsonIgnore
    public String getAuthoritiesAsString() {
    	StringBuilder sb = new StringBuilder();
    	
    	for (Autorizacija authority : this.authorities) {
    		sb.append(authority.getName() + " ");
    	}
    	
    	return sb.toString();
    }

	@Override
	public boolean isEnabled() {
		return active;
	}

	@Override
	public String getUsername() {
		return email;
	}
}


