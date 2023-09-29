package spring.edu.mongteacher.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import spring.edu.mongteacher.domain.Bank;
import spring.edu.mongteacher.domain.Teacher;
import spring.edu.mongteacher.dto.BankDTO;
import spring.edu.mongteacher.dto.TeacherDTO;

@Mapper(componentModel = "spring")
public interface ServerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherDTO dto, @MappingTarget Teacher entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBankFromDto(BankDTO dto, @MappingTarget Bank entity);
}