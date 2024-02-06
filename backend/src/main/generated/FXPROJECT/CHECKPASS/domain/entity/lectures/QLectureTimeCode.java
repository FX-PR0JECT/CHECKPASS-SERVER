package FXPROJECT.CHECKPASS.domain.entity.lectures;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureTimeCode is a Querydsl query type for LectureTimeCode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureTimeCode extends EntityPathBase<LectureTimeCode> {

    private static final long serialVersionUID = 470091484L;

    public static final QLectureTimeCode lectureTimeCode1 = new QLectureTimeCode("lectureTimeCode1");

    public final StringPath lectureTimeCode = createString("lectureTimeCode");

    public final NumberPath<Long> lectureTimeCodeId = createNumber("lectureTimeCodeId", Long.class);

    public QLectureTimeCode(String variable) {
        super(LectureTimeCode.class, forVariable(variable));
    }

    public QLectureTimeCode(Path<? extends LectureTimeCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureTimeCode(PathMetadata metadata) {
        super(LectureTimeCode.class, metadata);
    }

}

