package FXPROJECT.CHECKPASS.domain.enums;

public enum CollegesEnum {

    ConvergenceTechnology("융합기술대학"),
    Engineering("공과대학"),
    HumanitiesSocialSciences("인문사회대학"),
    HealthLifeSciences("보건생명대학"),
    Railway("철도대학"),
    FutureConvergence("미래융합대학"),
    FacultyOfLiberalArts("교양학부"),
    Free("자유전공학부"),
    CreativeConvergence("창의융합학부");


    private String college;

    CollegesEnum(String college) {
        this.college = college;
    }

    public String getCollege() {
        return college;
    }
}
