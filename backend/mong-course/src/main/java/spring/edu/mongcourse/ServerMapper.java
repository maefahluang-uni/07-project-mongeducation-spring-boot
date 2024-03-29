package spring.edu.mongcourse;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import spring.edu.mongcourse.model.Category;
import spring.edu.mongcourse.model.CategoryDTO;
import spring.edu.mongcourse.model.Course;
import spring.edu.mongcourse.model.CourseDTO;

@Mapper(componentModel = "spring")
public interface ServerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCourseFromDto(CourseDTO dto, @MappingTarget Course entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateCategoryFromDto(CategoryDTO dto, @MappingTarget Category entity);
}
