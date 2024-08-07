package za.co.shabisa.security.domain.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.shabisa.shared.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(name = "username")
  private String username;
  
  @Column(name = "password")
  private String password;
  
  @OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
  private List<UserRole> roles;

}
