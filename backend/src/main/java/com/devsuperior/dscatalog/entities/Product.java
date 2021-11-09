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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String name;
	@NonNull
	@Column(columnDefinition = "TEXT")
	private String description;
	@NonNull
	private Double price;
	@NonNull
	private String imgUrl;

	@Setter(value = AccessLevel.NONE)
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@NonNull
	private Instant date;

	@Setter(value = AccessLevel.NONE)
	@ManyToMany
	@JoinTable(name = "tb_product_category",
				joinColumns = @JoinColumn(name= "product_id"),
				inverseJoinColumns =  @JoinColumn(name= "category_id"))
	private Set<Category> categories = new HashSet<>();

}
