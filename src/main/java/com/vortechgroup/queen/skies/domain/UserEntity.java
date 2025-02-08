package com.vortechgroup.queen.skies.domain;

import com.vortechgroup.queen.skies.dto.request.CreateUserDto;
import com.vortechgroup.queen.skies.dto.request.UpdateUserDto;
import com.vortechgroup.queen.skies.dto.response.UserResponseDto;
import com.vortechgroup.queen.skies.utils.ToDTO;
import com.vortechgroup.queen.skies.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_user",schema = "auth")
public class UserEntity implements TransformFrom<CreateUserDto,UserEntity>, ToDTO<UserResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id_generated")
    @SequenceGenerator(name = "seq_user_id_generated", allocationSize = 1,
            sequenceName = "seq_user_id",schema = "auth")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "fullname",updatable = false)
    private String fullname;

    @Column(name = "username",updatable = false)
    private String username;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "active_status")
    private Character activeStatus;

    @Column(name = "password")
    private String password;

    @Column(name = "created_on",updatable = false)
    private LocalDate createdOn;

    @Override
    public UserResponseDto toDto() {
        return UserResponseDto.builder()
                .id(this.id)
                .fullname(this.fullname)
                .username(this.username)
                .roleId(this.roleId)
                .activeStatus(this.activeStatus)
                .createdOn(this.createdOn)
                .build();
    }

    @Override
    public UserEntity from(CreateUserDto createUserDto) {
        return UserEntity.builder()
                .fullname(createUserDto.getFullname())
                .username(createUserDto.getUsername())
                .roleId(createUserDto.getRoleId())
                .activeStatus(createUserDto.getActiveStatus())
                .password(createUserDto.getPassword())
                .createdOn(LocalDate.now())
                .build();
    }

    public UserEntity from(UpdateUserDto updateUserDto) {
        return UserEntity.builder()
                .id(updateUserDto.getId())
                .roleId(updateUserDto.getRoleId())
                .activeStatus(updateUserDto.getActiveStatus())
                .password(updateUserDto.getPassword())
                .build();
    }
}
