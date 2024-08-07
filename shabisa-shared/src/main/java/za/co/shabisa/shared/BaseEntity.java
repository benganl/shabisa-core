package za.co.shabisa.shared;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID, generator = "uuid2")
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "datetime_created")
  protected LocalDateTime dateTimeCreated;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "datetime_updated")
  protected LocalDateTime dateTimeUpdated;

  @PrePersist
  void prePersist() {
    if (Objects.isNull(id)) {
      id = UUID.randomUUID();
    }

    if (Objects.isNull(dateTimeCreated)) {
      dateTimeCreated = LocalDateTime.now();
    }

    if (Objects.isNull(dateTimeUpdated)) {
      dateTimeUpdated = LocalDateTime.now();
    }
  }

  @PreUpdate
  void preUpdate() {
    dateTimeUpdated = LocalDateTime.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
