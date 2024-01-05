package FXPROJECT.CHECKPASS.domain.common.constant;

public enum ErrorCode {

    DUPLICATION_USERS(404,"USERS-001","이미 존재하는 회원입니다."),
    FAIL_JOIN(500,"JOIN-001","회원 가입 실패"),
    NO_SUCH_USER(404,"USERS-002","존재하지 않는 유저입니다."),
    INTERNAL_ERROR(500,"SERVER-001","서버에서 처리할 수 없습니다."),
    INVALID_REQUEST(404,"INVALID-001","유효하지 않은 요청입니다."),
    SERCH_FAIL(404,"SEARCH-0001","검색 결과가 존재하지 않습니다."),
    NO_AUTHENTICATION(404,"AUTH-0001","인증되지 않은 사용자입니다.")
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