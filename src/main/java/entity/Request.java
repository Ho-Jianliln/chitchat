package entity;

public class Request {
    private int id;
    private String applicant;
    private String target;
    private String context;
    private int type;
    private int status;

    public Request(){}
    public Request(String applicant, String target, String context, int type, int status) {
        this.applicant = applicant;
        this.target = target;
        this.context = context;
        this.type = type;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", applicant='" + applicant + '\'' +
                ", target='" + target + '\'' +
                ", context='" + context + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
