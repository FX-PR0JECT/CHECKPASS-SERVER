package FXPROJECT.CHECKPASS.domain.common.constant;

public enum ErrorCode {

    DUPLICATION_USERS(404,"USERS-001","이미 존재하는 회원입니다."),
    FAIL_JOIN(500,"JOIN-001","회원 가입 실패"),

    ;

    private final int status;
    private final String code;
    private final String description;

    ErrorCode(int status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}