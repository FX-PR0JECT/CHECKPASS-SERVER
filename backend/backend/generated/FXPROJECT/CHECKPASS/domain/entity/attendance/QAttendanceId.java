package FXPROJECT.CHECKPASS.domain.entity.attendance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceId is a Querydsl query type for AttendanceId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAttendanceId extends BeanPath<AttendanceId> {

    private static final long serialVersionUID = 1931180812L;

    public static final QAttendanceId attendanceId = new QAttendanceId("attendanceId");

    public final NumberPath<Long> lectureCode = createNumber("lectureCode", Long.class);

    public final StringPath lectureDay = createString("lectureDay");

    public final StringPath studentGrade = createString("studentGrade");

    public final NumberPath<Long> studentId = createNumber("studentId", Long.class);

    public final StringPath studentSemester = createString("studentSemester");

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public QAttendanceId(String variable) {
        super(AttendanceId.class, forVariable(variable));
    }

    public QAttendanceId(Path<? extends AttendanceId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceId(PathMetadata metadata) {
        super(AttendanceId.class, metadata);
    }

}

