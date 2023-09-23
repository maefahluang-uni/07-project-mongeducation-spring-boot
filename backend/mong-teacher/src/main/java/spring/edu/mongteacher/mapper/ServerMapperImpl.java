package spring.edu.mongteacher.mapper;

import org.springframework.stereotype.Component;

import spring.edu.mongteacher.domain.Teacher;
import spring.edu.mongteacher.dto.TeacherDTO;

@Component
public class ServerMapperImpl implements ServerMapper {
    
    @Override
    public void updateTeacherFromDto(TeacherDTO dto, Teacher entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if(dto.getBankID() != null){
            entity.setBankID(dto.getBankID());
        }
        if(dto.getFirstName() != null){
            entity.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null){
            entity.setLastName(dto.getLastName());
        }
        if(dto.getIdCard() != null){
            entity.setIdCard(dto.getIdCard());
        }
    }

}
