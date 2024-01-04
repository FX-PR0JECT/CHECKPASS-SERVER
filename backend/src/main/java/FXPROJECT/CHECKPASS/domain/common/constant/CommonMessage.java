package FXPROJECT.CHECKPASS.domain.common.constant;

public enum CommonMessage {

    COMPLETE_JOIN("가입이 완료되었습니다."),
    AVAILABLE("사용 가능합니다."),
    COMPLETE_DELETE("삭제가 완료 되었습니다.")
    ;


    private final String description;

    CommonMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}