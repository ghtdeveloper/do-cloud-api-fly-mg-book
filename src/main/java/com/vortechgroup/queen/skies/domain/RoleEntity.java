package com.vortechgroup.queen.skies.domain;


import com.vortechgroup.queen.skies.dto.request.CreateRoleDto;
import com.vortechgroup.queen.skies.dto.request.UpdateRoleDto;
import com.vortechgroup.queen.skies.dto.response.RoleResponseDto;
import com.vortechgroup.queen.skies.utils.ToDTO;
import com.vortechgroup.queen.skies.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_role",schema = "auth")
public class RoleEntity implements TransformFrom<CreateRoleDto,RoleEntity>, ToDTO<RoleResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_id_generated")
    @SequenceGenerator(name = "seq_role_id_generated", allocationSize = 1,
            sequenceName = "seq_role_id",schema = "auth")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active_status")
    private Character activeStatus;

    @Override
    public RoleResponseDto toDto() {
        return RoleResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .activeStatus(this.activeStatus)
                .build();
    }
    @Override
    public RoleEntity from(CreateRoleDto createRoleDto) {
        return RoleEntity.builder()
                .name(createRoleDto.getName())
                .activeStatus(createRoleDto.getActiveStatus())
                .build();
    }

    public RoleEntity from(UpdateRoleDto updateRoleDto) {
        return RoleEntity.builder()
                .id(updateRoleDto.getId())
                .name(updateRoleDto.getName())
                .activeStatus(updateRoleDto.getActiveStatus())
                .build();
    }
}
