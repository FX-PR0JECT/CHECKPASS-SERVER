package FXPROJECT.CHECKPASS.domain.enums;

public enum CollegesEnum {

    ConvergenceTechnology("융합기술대학",1l),
    Engineering("공과대학",2l),
    HumanitiesSocialSciences("인문사회대학",3l),
    HealthLifeSciences("보건생명대학",4l),
    Railway("철도대학",5l),
    FutureConvergence("미래융합대학",6l),
    FacultyOfLiberalArts("교양학부",7l),
    Free("자유전공학부",8l),
    CreativeConvergence("창의융합학부",9l);


    private String college;
    private Long collegeCode;

    CollegesEnum(String college, Long collegeCode) {
        this.college = college;
        this.collegeCode = collegeCode;
    }

    public String getCollege() {
        return college;
    }

    public Long getCollegeCode() {
        return collegeCode;
    }
}
