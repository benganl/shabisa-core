package za.co.shabisa.security.domain.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import za.co.shabisa.shared.BaseEntity;

public class UserRole extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(optional = false)
  @JoinColumn(name="userRef", referencedColumnName = "id", nullable=false, updatable=false)
  private User user;
  
  @ManyToOne(optional = false)
  @JoinColumn(name="roleRef", referencedColumnName = "id", nullable=false, updatable=false)
  private Role role;
}
