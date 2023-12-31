package FXPROJECT.CHECKPASS.domain.enums;

public enum Job {

    PROFESSOR("교수"),STUDENTS("학생"),STAFF("직원");

    private String job;

    Job(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }
}
