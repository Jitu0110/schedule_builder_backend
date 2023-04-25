package team.pixel.schedule_builder_backend.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.annotation.Nullable;


@Document(indexName = "courses")
@Data
public class Course {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String courseName;

    @Field(type = FieldType.Text)
    private String courseInstructor;

    @Field(type = FieldType.Text)
    private String courseVenue;

    @Field(type = FieldType.Text)
    private String courseCode;

    @Field(type = FieldType.Boolean)
    private Boolean isRecurring;

    @Field(type = FieldType.Text)
    private String courseDescription;

    @Field(type = FieldType.Text)
    private String courseStartTime;

    @Field(type = FieldType.Text)
    private String courseEndTime;

}

