package com.devsuperior.dscatalog.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@NonNull
	private Long id;
	@NonNull
	@Size(max = 60, min = 5, message = "campo nome deve ter  5 a  60")
	@NotBlank(message = "nome obrigatorio")
	private String name;
	@NonNull
	private String description;
	@NonNull
	@Positive (message = "preço não pode ser neg")
	private Double price;
	@NonNull
	private String imgUrl;
	
	@NonNull
	@PastOrPresent(message = "data do prod não pode ser futura")
	private Instant date;

	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}

	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
	}
	
	

}
