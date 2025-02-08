package com.vortechgroup.queen.skies.controllers;


import com.vortechgroup.queen.skies.dto.request.CreateUserDto;
import com.vortechgroup.queen.skies.dto.request.UpdateUserDto;
import com.vortechgroup.queen.skies.dto.response.UserCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.UserResponseDto;
import com.vortechgroup.queen.skies.services.UserService;
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
@RequestMapping(value = "api/v1.0/api/fly/user", produces = MediaType.APPLICATION_JSON_VALUE)
public record UserController(UserService userService) {

    @PostMapping(value = "/save")
    @Operation(summary = "Method to save a user", description = "Method to save a user")
    public ResponseEntity<UserResponseDto> save(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(createUserDto));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Method to update a user", description = "Method to update a user")
    public ResponseEntity<UserResponseDto> update(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.update(updateUserDto));
    }

    @GetMapping(value = "/{id}/{countryId}")
    @Operation(summary = "Search by user id", description = "Search by user id")
    public ResponseEntity<UserResponseDto> findById(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @PathVariable(name = "id") Long id, @NotNull @PathVariable(name = "countryId") Long countryId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findById(id, countryId));
    }

    @GetMapping(value = "/detail")
    @Operation(summary = "Search by username", description = "Search by user username")
    public ResponseEntity<UserResponseDto> findByName(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @NotNull @RequestParam(name = "username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findByName(username));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of users", description = "Return a list of users")
    public ResponseEntity<UserCollectionResponse> findAll(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                          @Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findAll(page, pageSize));
    }

}
