package server;

public class Response {
    private final String response;
    private final String reason;
    private final String value;

    private Response(Builder builder) {
        response = builder.response;
        reason = builder.reason;
        value = builder.value;
    }

    public static class Builder {
        private final String response;
        private String reason;
        private String value;

        public Builder(String response) {
            this.response = response;
        }

        public Builder reason(String val) {
            reason = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
