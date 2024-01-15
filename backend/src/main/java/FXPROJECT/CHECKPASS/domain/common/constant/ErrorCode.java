package FXPROJECT.CHECKPASS.domain.common.constant;

public enum ErrorCode {

    MISSING_REQUIRED_ELEMENT(403,-3,"Missing required element","해당 API를 사용하기 위해 필요한 기능(간편가입, 동의항목, 서비스 설정 등)이 활성화 되지 않은 경우\n" +
            "해결 방법: [내 애플리케이션]에서 필요한 기능을 선택한 후, \n" +
            "[활성화 설정]에서 ON 으로 설정한 후 재 호출"),
    SERVER_MAINTENANCE(400,-7,"Server maintenance","서비스 점검 또는 내부 문제가 있는 경우\n" +
            "해결 방법: 해당 서비스 공지사항 확인"),
    INVALID_HEADER(400,-8,"Invalid header","올바르지 않은 헤더로 요청한 경우\n" +
            "해결 방법: 요청 헤더 확인"),
    SERVICE_TERMINATED(400,-8,"Service terminated","서비스가 종료된 API를 호출한 경우"),
    REQUEST_COUNT_EXCEEDED(400,-10,"Request count exceeded","허용된 요청 회수를 초과한 경우"),
    INFORMATION_DISCREPANCY(401,-401,"Information discrepancy","유효하지 않은 앱키나 액세스 토큰으로 요청한 경우, 등록된 앱 정보와 호출된 앱 정보가 불일치 하는 경우"),
    IMAGE_CAPACITY_EXCEEDED(400,-602,"Image capacity exceeded","이미지 업로드 시 최대 용량을 초과한 경우"),
    TIME_OUT(400,-603,"Time out"," 내부에서 요청 처리 중 타임아웃이 발생한 경우"),
    NUMBER_OF_IMAGES_EXCEEDED(400,-606,"Number of images exceeded","업로드할 수 있는 최대 이미지 개수를 초과한 경우"),
    IMAGE_FORMAT_ERROR(400,-911,"Image format error","지원하지 않는 포맷의 이미지를 업로드 하는 경우"),
    UNDER_MAINTENANCE(503,-9798,"Under maintenance","서비스 점검중"),
    UNAUTHENTICATED_USER(403,-20,"Unauthenticated user","Database 에 존재하지 않는 회원 , 미가입 또는 유예 사용자가 API를 호출한 경우\n" +
            "해결 방법 :  확인 후 재 요청"),
    EXISTING_USER(403,-21,"Existing user","Database 에 이미 존재하는 회원인 경우\n" +
            "해결 방법 :  확인 후 재 요청"),
    NON_EXISTING_LECTURE(400, -100, "Non-Existing Lecture", "Database에 등롣되지 않은 강의\n" + "해결 방법 : 확인 후 재 요청"),
    EXISTING_LECTURE(400, -101, "Existing Lecture", "Database에 이미 등록된 강의\n" + "해결 방법 : 확인 후 재 요청"),
    INVALID_ROLE_REQUEST(403,-22,"Invalid Role request","유효하지 않은 요청\n" +
            "해결 방법 : 회원 Job 확인 후 재 요청"),
    SANCTIONS_USER(403,-23,"sanctions user","계정이 제재된 경우나 해당 계정에 제재된 행동을 하는 경우"),
    NO_PERMISSION(403,-24,"No permission","해당 API에 대한 요청 권한이 없는 경우\n" +
            "해결 방법: 해당 API의 이해하기 문서를 참고하여 검수 진행, 권한 획득 후 재호출"),
    NO_SEARCH_RESULTS_FOUND(400,-60,"No search results found","검색 결과가 존재하지 않음.\n" +
            "해결 방법 :  검색 조건 확인 후 재 요청"),
    BAD_URI_REQUEST(400,-61,"Bad request","잘못된 URI 요청"),
    MISSING_REQUIRED_ARGUMENT(400,-62,"Missing required argument","필수 인자가 포함되지 않은 경우나 호출 인자값의 데이타 타입이 적절하지 않거나 허용된 범위를 벗어난 경우\n" +
            "해결 방법: 요청 파라미터 확인"),
    SERVER_ERROR(500,-80,"Server Error","서버 내부에서 처리 중에 에러가 발생한 경우\n" +
            "해결 방법: 재시도"),
    UNKNOWN_SERVER_ERROR(500,-81,"Unknown server error","서버에서 처리 할 수 없음"),
    OK(200,-1000,"OK","OK");

    private final int status;
    private final Integer code;
    private final String title;
    private final String description;

    ErrorCode(int status, Integer code,String title ,String description) {
        this.status = status;
        this.code = code;
        this.description = description;
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}