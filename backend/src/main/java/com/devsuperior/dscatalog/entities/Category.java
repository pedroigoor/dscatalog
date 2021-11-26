package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.devsuperior.dscatalog.dtos.CategoryDTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
	
@NoArgsConstructor
@Data	
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Setter(value = AccessLevel.NONE)
	private Instant createdAt;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Setter(value = AccessLevel.NONE)
	private Instant updatedAt;

	@ManyToMany(mappedBy = "categories")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Product> products = new HashSet<Product>();
	
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category(CategoryDTO dto) {
		this.name = dto.getName();
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = Instant.now();

	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = Instant.now();

	}

	

}