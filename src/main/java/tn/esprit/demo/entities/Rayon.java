package tn.esprit.demo.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Rayon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idRayon")
	private Long idRayon;
	private String code;
	private String libelle;
	
	// One to many association Rayon 1-* Produit
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rayon")
	@ToString.Exclude
	private Set<Produit> P;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Rayon rayon = (Rayon) o;
		return idRayon != null && Objects.equals(idRayon, rayon.idRayon);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
