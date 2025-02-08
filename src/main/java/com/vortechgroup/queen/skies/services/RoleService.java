package com.vortechgroup.queen.skies.services;


import com.vortechgroup.queen.skies.domain.RoleEntity;
import com.vortechgroup.queen.skies.dto.request.CreateRoleDto;
import com.vortechgroup.queen.skies.dto.request.UpdateRoleDto;
import com.vortechgroup.queen.skies.dto.response.RoleCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.RoleResponseDto;
import com.vortechgroup.queen.skies.repository.RoleRepository;
import com.vortechgroup.queen.skies.utils.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public RoleResponseDto save(CreateRoleDto createRoleDto) {
        RoleEntity roleEntity = RoleEntity.builder().build().from(createRoleDto);
        return roleRepository.save(roleEntity).toDto();
    }

    @Transactional
    public RoleResponseDto update(UpdateRoleDto updateRoleDto) {
        if (findById(updateRoleDto.getId()) != null) {
            RoleEntity roleEntity = RoleEntity.builder().build().from(updateRoleDto);
            return roleRepository.save(roleEntity).toDto();
        }
        return null;
    }

    public RoleResponseDto findById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("roleId does not exist"));
        return roleEntity.toDto();
    }

    public RoleCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<RoleEntity> entityPage = roleRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id")));
        return RoleCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(entityPage.getTotalPages() - 1)
                .totalElements(entityPage.getTotalElements())
                .roleResponses(entityPage.stream().map(RoleEntity::toDto).collect(Collectors.toList()))
                .build();
    }
}
