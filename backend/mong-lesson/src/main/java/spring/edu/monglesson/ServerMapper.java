package spring.edu.monglesson;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import spring.edu.monglesson.model.Lesson;
import spring.edu.monglesson.model.LessonDTO;
import spring.edu.monglesson.model.Note;
import spring.edu.monglesson.model.NoteDTO;
import spring.edu.monglesson.model.Video;
import spring.edu.monglesson.model.VideoDTO;

@Mapper(componentModel = "spring")
public interface ServerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateLessonFromDto(LessonDTO dto, @MappingTarget Lesson entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateNoteFromDto(NoteDTO dto, @MappingTarget Note entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateVideoFromDto(VideoDTO dto, @MappingTarget Video entity);
}
