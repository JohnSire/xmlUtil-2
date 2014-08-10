/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 10.08.2014 16:35
 */
public enum SupportedCommands {
    VALIDATE {
        // xmlPath, schemaPath, schemaType
        private final int params = 3;

        @Override
        public int params() {
            return params;
        }
    },
    GET {
        // xmlPath, elemName
        private final int params = 2;

        @Override
        public int params() {
            return params;
        }
    };

    public abstract int params();
}
