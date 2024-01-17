package FXPROJECT.CHECKPASS.domain.entity.college;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QColleges is a Querydsl query type for CollegesEnum
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QColleges extends EntityPathBase<Colleges> {

    private static final long serialVersionUID = 2142350224L;

    public static final QColleges colleges = new QColleges("colleges");

    public final StringPath college = createString("college");

    public final NumberPath<Long> collegeId = createNumber("collegeId", Long.class);

    public QColleges(String variable) {
        super(Colleges.class, forVariable(variable));
    }

    public QColleges(Path<? extends Colleges> path) {
        super(path.getType(), path.getMetadata());
    }

    public QColleges(PathMetadata metadata) {
        super(Colleges.class, metadata);
    }

}

