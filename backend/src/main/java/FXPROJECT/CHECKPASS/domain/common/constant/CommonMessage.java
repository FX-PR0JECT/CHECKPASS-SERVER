package FXPROJECT.CHECKPASS.domain.common.constant;

public enum CommonMessage {

    COMPLETE_JOIN("가입이 완료되었습니다."),
    AVAILABLE_ID("사용 가능한 아이디입니다."),
    COMPLETE_DELETE("삭제가 완료되었습니다."),
    COMPLETE_UPDATE("수정이 완료되었습니다."),
    COMPLETE_REGISTER("등록이 완료되었습니다."),
    COMPLETE_ENROLLMENT("신청이 완료되었습니다."),
    COMPLETE_ATTENDANCE("출석체크가 완료되었습니다."),
    TREAT_LATENESS("지각 처리되었습니다."),
    COURSE_CANCELLATION_COMPLETED("수강 취소가 완료되었습니다."),
    NOT_FOUNT("찾을 수 없습니다."),
    SUCCESS_LOGIN("로그인 성공!"),
    FAIL_LOGIN("로그인 실패"),
    FAIL_REGISTER("등록 실패"),
    FAIL_DELETE("삭제 실패"),
    SUCCESS_LOGOUT("로그아웃 완료!");


    private final String description;

    CommonMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
