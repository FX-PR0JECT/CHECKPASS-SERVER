package FXPROJECT.CHECKPASS.domain.entity.lectures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = 2022694498L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLecture lecture = new QLecture("lecture");

    public final StringPath dayOrNight = createString("dayOrNight");

    public final NumberPath<Long> lectureCode = createNumber("lectureCode", Long.class);

    public final NumberPath<Integer> lectureCount = createNumber("lectureCount", Integer.class);

    public final NumberPath<Integer> lectureFull = createNumber("lectureFull", Integer.class);

    public final NumberPath<Integer> lectureGrade = createNumber("lectureGrade", Integer.class);

    public final NumberPath<Integer> lectureGrades = createNumber("lectureGrades", Integer.class);

    public final StringPath lectureKind = createString("lectureKind");

    public final StringPath lectureName = createString("lectureName");

    public final StringPath lectureRoom = createString("lectureRoom");

    public final StringPath lectureTimes = createString("lectureTimes");

    public final FXPROJECT.CHECKPASS.domain.entity.users.QProfessor professor;

    public QLecture(String variable) {
        this(Lecture.class, forVariable(variable), INITS);
    }

    public QLecture(Path<? extends Lecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLecture(PathMetadata metadata, PathInits inits) {
        this(Lecture.class, metadata, inits);
    }

    public QLecture(Class<? extends Lecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.professor = inits.isInitialized("professor") ? new FXPROJECT.CHECKPASS.domain.entity.users.QProfessor(forProperty("professor"), inits.get("professor")) : null;
    }

}

