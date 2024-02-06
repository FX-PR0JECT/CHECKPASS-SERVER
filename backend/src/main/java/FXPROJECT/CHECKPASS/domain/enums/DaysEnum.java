package FXPROJECT.CHECKPASS.domain.enums;

public enum DaysEnum {

    Monday("월"),
    Tuesday("화"),
    Wednesday("수"),
    Thursday("목"),
    Friday("금"),
    Saturday("토"),
    Sunday("일");

    private String day;

    DaysEnum(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
