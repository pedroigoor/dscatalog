package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.devsuperior.dscatalog.dtos.CategoryDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
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
	@NonNull
	private Long id;
	@NonNull
	private String name;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Setter(value = AccessLevel.NONE)
	private Instant createdAt;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@Setter(value = AccessLevel.NONE)
	private Instant updatedAt;

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