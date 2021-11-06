package com.example.saas;

import com.example.saas.rbac.dto.RoleAddInputDto;

import com.example.saas.rbac.dto.RoleUpdateDto;
import com.example.saas.rbac.dto.UserAddInputDto;
import com.example.saas.rbac.dto.UserUpdateDto;
import com.example.saas.rbac.entitys.Role;
import com.example.saas.rbac.entitys.User;
import com.example.saas.staff.dto.AddStaffDto;
import com.example.saas.staff.dto.UpdateStaffDto;
import com.example.saas.staff.entitys.Staff;
import com.example.saas.passport.dto.ForgetPasswordUserDto;
import com.example.saas.passport.dto.RegisterInputDto;
import com.example.saas.passport.dto.UserLoginDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMapper {

    AutoMapper INSTANCE = Mappers.getMapper( AutoMapper.class );

    User LoginDtoToUser(UserLoginDto userLoginDto);

    User RegisterDtoToUser(RegisterInputDto registerInputDto);

    User ForgetDtoToUser(ForgetPasswordUserDto forgetPasswordUserDto);

    Staff AddDtoToStaff(AddStaffDto addStaffDto);
    Staff UpdateDtoToStaff(UpdateStaffDto updateStaffDto);
    Role roleAddDtoToRole(RoleAddInputDto addRoleDto);
    Role updateDtoToRole(RoleUpdateDto roleUpdateDto);
    User addDtoToUser(UserAddInputDto userAddInputDto);
    User updateDtoToUser(UserUpdateDto userUpdateDto);

    // Userr updateDtoToUserr(UserUpdateDto userUpdateDto);













}
