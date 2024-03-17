package FXPROJECT.CHECKPASS.domain.enums;

public enum LectureKind {

    // 전필, 전선, 자선, 산학, 교양, 교직, 마선, 마필, 융선, 융필, K융필

    MANDATORY("전필"),CHOICE("전선"),AUTONOMY("자선"),INDUSTRY("산학"),ELECTIVE("교양");

    private String lectureKind;

    LectureKind(String lectureKind) {
        this.lectureKind = lectureKind;
    }

    public String getKind() {
        return lectureKind;
    }
}