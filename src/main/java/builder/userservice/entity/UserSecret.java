package builder.userservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "user_secrets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSecret {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @OneToOne(mappedBy = "userSecret", cascade = CascadeType.ALL)
    private User user;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_secret_id", referencedColumnName = "id")
    private List<PasswordSecret> passwordSecretList = new ArrayList<>();
}
