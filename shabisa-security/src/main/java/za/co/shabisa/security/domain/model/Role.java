package za.co.shabisa.security.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.shabisa.shared.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String name;
  private String description;
}
