package zw.co.malvern.utils.response;

public class Response {
    private String narrative;
    private boolean success;

    public Response() {
    }

    public Response(String narrative, boolean success) {
        this.narrative = narrative;
        this.success = success;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
