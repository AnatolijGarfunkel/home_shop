package project.converter;

public interface ResponseConverter <Entity, Response>{

    Response toDto(Entity entity);
}
