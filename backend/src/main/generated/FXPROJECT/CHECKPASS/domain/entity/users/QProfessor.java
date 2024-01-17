package FXPROJECT.CHECKPASS.domain.entity.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfessor is a Querydsl query type for Professor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfessor extends EntityPathBase<Professor> {

    private static final long serialVersionUID = 2042169100L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfessor professor = new QProfessor("professor");

    public final QUsers _super;

    // inherited
    public final QAccount account;

    // inherited
    public final FXPROJECT.CHECKPASS.domain.entity.college.QDepartments departments;

    public final StringPath HIREDATE = createString("HIREDATE");

    //inherited
    public final NumberPath<Long> userId;

    //inherited
    public final EnumPath<FXPROJECT.CHECKPASS.domain.enums.Job> userJob;

    //inherited
    public final StringPath userName;

    public QProfessor(String variable) {
        this(Professor.class, forVariable(variable), INITS);
    }

    public QProfessor(Path<? extends Professor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfessor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfessor(PathMetadata metadata, PathInits inits) {
        this(Professor.class, metadata, inits);
    }

    public QProfessor(Class<? extends Professor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QUsers(type, metadata, inits);
        this.account = _super.account;
        this.departments = _super.departments;
        this.userId = _super.userId;
        this.userJob = _super.userJob;
        this.userName = _super.userName;
    }

}

