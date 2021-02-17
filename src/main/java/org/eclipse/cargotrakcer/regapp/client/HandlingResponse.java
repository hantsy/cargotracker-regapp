package org.eclipse.cargotrakcer.regapp.client;

sealed public interface HandlingResponse permits
        HandlingResponse.OK,
        HandlingResponse.FAILED {

    final class OK implements HandlingResponse {
    }

    final class FAILED implements HandlingResponse {
        private final String message;

        public FAILED(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
