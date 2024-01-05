package FXPROJECT.CHECKPASS.domain.common.constant;

public enum CommonMessage {

    COMPLETE_JOIN("가입이 완료되었습니다."),
    AVAILABLE("사용 가능합니다."),
    COMPLETE_DELETE("삭제가 완료 되었습니다."),
    COMPLETE_UPDATE("수정이 완료 되었습니다."),
    NOT_FOUNT("찾을 수 없습니다."),
    SUCCESS_LOGIN("로그인 성공!"),
    FAIL_LOGIN("로그인 실패"),
    SUCCESS_LOGOUT("로그아웃 완료!")
    ;


    private final String description;

    CommonMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
