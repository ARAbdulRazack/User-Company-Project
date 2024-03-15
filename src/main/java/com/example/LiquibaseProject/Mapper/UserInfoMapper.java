package com.example.LiquibaseProject.Mapper;

import com.example.LiquibaseProject.DTO.UserInfoRequestDTO;
import com.example.LiquibaseProject.DTO.UserInfoResponseDTO;
import com.example.LiquibaseProject.Model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    UserInfo mapToEntity(UserInfoRequestDTO userRequestDTO);

    UserInfoResponseDTO mapToDTO(UserInfo userInfo);

    @Mapping(target = "id", ignore = true) // Ignore mapping for id during updates
    void updateEntityFromDTO(UserInfoRequestDTO userInfoRequestDTO, @MappingTarget UserInfo userInfo);
}
