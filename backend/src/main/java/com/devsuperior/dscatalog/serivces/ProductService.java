package com.devsuperior.dscatalog.serivces;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dtos.CategoryDTO;
import com.devsuperior.dscatalog.dtos.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.excptions.DataBaseException;
import com.devsuperior.dscatalog.excptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.repositories.CategoryRespository;
import com.devsuperior.dscatalog.repositories.ProductRespository;

@Service
public class ProductService {

	@Autowired
	private ProductRespository repository;
	@Autowired
	private CategoryRespository categoryRespository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(ProductDTO::new);
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
		return new ProductDTO(entity, entity.getCategories());

	}

	@Transactional
	public ProductDTO save(ProductDTO dto) {
		Product enity = new Product();
		copyDtoToEntity(dto, enity);
		enity = repository.save(enity);
		return new ProductDTO(enity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product enity = repository.getOne(id);
			copyDtoToEntity(dto, enity);
			enity = repository.save(enity);
			return new ProductDTO(enity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não existente " + id);
		}

	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não existente " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violantion");
		}

	}

	private void copyDtoToEntity(ProductDTO dto, Product enity) {
		enity.setName(dto.getName());
		enity.setDescription(dto.getDescription());
		enity.setImgUrl(dto.getImgUrl());
		enity.setPrice(dto.getPrice());
		enity.setDate(dto.getDate());
		
		enity.getCategories().clear();
		for(CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRespository.getOne(catDto.getId());
			enity.getCategories().add(category);
		}
		
	}

}
