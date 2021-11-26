package com.devsuperior.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dtos.RoleDTO;
import com.devsuperior.dscatalog.dtos.UserDTO;
import com.devsuperior.dscatalog.dtos.UserInsertDTO;
import com.devsuperior.dscatalog.dtos.UserUpdateDTO;
import com.devsuperior.dscatalog.entities.Role;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.excptions.DataBaseException;
import com.devsuperior.dscatalog.excptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.repositories.RoleRepository;
import com.devsuperior.dscatalog.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRpository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(UserDTO::new);
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
		return new UserDTO(entity);

	}

	@Transactional
	public UserDTO save(UserInsertDTO dto) {
		User enity = new User();
		copyDtoToEntity(dto, enity);
		enity.setPassword(passwordEncoder.encode(dto.getPassword()));
		enity = repository.save(enity);
		return new UserDTO(enity);
	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO dto) {
		try {
			User enity = repository.getOne(id);
			copyDtoToEntity(dto, enity);
			enity = repository.save(enity);
			return new UserDTO(enity);
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

	private void copyDtoToEntity(UserDTO dto, User enity) {
		enity.setFirstName(dto.getFirstName());
		enity.setLastName(dto.getLastName());
		enity.setEmail(dto.getEmail());
		
		enity.getRoles().clear();
		for(RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRpository.getOne(roleDto.getId());
			enity.getRoles().add(role);
		}
		
	}

}
