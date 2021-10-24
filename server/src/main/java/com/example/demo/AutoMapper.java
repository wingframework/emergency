package com.example.demo;

import com.example.demo.Rbac.Dto.RoleAddInputDto;

import com.example.demo.Rbac.Dto.UserrAddInputDto;
import com.example.demo.Rbac.Entitys.Role;
import com.example.demo.Rbac.Entitys.Userr;
import com.example.demo.Staff.Dto.AddStaffDto;
import com.example.demo.Staff.Dto.UpdateStaffDto;
import com.example.demo.Staff.entitys.Staff;
import com.example.demo.passport.Dto.ForgetPasswordUserDto;
import com.example.demo.passport.Dto.RegisterInputDto;
import com.example.demo.passport.Dto.UserLoginDto;
import com.example.demo.passport.entitys.User;
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
   // Role updateDtoToRole(UpdateRoleDto updateRoleDto);
    Userr addDtoToUserr(UserrAddInputDto userrAddInputDto);
    // Userr updateDtoToUserr(UserUpdateDto userUpdateDto);













}
