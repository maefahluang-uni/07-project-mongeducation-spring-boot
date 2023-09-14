package spring.edu.mongcourse;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import spring.edu.mongcourse.model.Course;
import spring.edu.mongcourse.model.CourseDTO;

@Mapper(componentModel = "spring")
public interface ServerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCourseFromDto(CourseDTO dto, @MappingTarget Course entity);

}
