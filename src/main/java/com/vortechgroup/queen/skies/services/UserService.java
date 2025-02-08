package com.vortechgroup.queen.skies.services;


import com.vortechgroup.queen.skies.domain.UserEntity;
import com.vortechgroup.queen.skies.dto.request.CreateUserDto;
import com.vortechgroup.queen.skies.dto.request.UpdateUserDto;
import com.vortechgroup.queen.skies.dto.response.UserCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.UserResponseDto;
import com.vortechgroup.queen.skies.repository.UserRepository;
import com.vortechgroup.queen.skies.utils.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Transactional
    public UserResponseDto save(CreateUserDto createUserDto) {
        roleService.findById(createUserDto.getRoleId());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = UserEntity.builder().build().from(createUserDto);
        userEntity.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userRepository.save(userEntity);
        return userEntity.toDto();
    }

    @Transactional
    public UserResponseDto update(UpdateUserDto updateUserDto) {
        if (roleService.findById(updateUserDto.getRoleId()) != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            findById(updateUserDto.getId());
            UserEntity userEntity = UserEntity.builder().build().from(updateUserDto);
            userEntity.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
            userRepository.save(userEntity);
            return userEntity.toDto();
        }
        return null;
    }

    public UserResponseDto findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("userId does not exist"));
        return userEntity.toDto();
    }

    public UserResponseDto findByName(String userName) {
        UserEntity userEntity = userRepository.findByUsername(userName).orElseThrow(() -> new NotFoundException("username does not exist"));
        return userEntity.toDto();
    }

    public UserCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<UserEntity> entityPage = userRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id")));
        return UserCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(entityPage.getTotalPages() - 1)
                .totalElements(entityPage.getTotalElements())
                .userResponses(entityPage.stream().map(UserEntity::toDto).collect(Collectors.toList()))
                .build();
    }

}
