package server;

public class Service {
    private Message message;
    private Response response;

    public Service(Message message) {
        this.message = message;
    }

    public Response getResponse() {
        return response;
    }

    public Message getMessage() {
        return message;
    }
}
