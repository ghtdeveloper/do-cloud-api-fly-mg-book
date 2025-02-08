package com.vortechgroup.queen.skies.controllers;


import com.vortechgroup.queen.skies.dto.request.CreateRoleDto;
import com.vortechgroup.queen.skies.dto.request.UpdateRoleDto;
import com.vortechgroup.queen.skies.dto.response.RoleCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.RoleResponseDto;
import com.vortechgroup.queen.skies.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1.0/api/fly/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    private final RoleService roleService;

    @PostMapping(value = "/save")
    @Operation(summary = "Method to save a role", description = "Method to save a role")
    public ResponseEntity<RoleResponseDto> save(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody CreateRoleDto createRoleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.save(createRoleDto));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Method to update a role", description = "Method to update a role")
    public ResponseEntity<RoleResponseDto> update(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody UpdateRoleDto updateRoleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.update(updateRoleDto));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search by role id", description = "Search by role id")
    public ResponseEntity<RoleResponseDto> findById(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.findById(id));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of roles", description = "Return a list of roles")
    public ResponseEntity<RoleCollectionResponse> findAll(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                          @Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.findAll(page, pageSize));
    }

}
