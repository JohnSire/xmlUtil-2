package validator;

import validator.dtd.DtdValidator;
import validator.xsd.XsdValidator;

/**
 * Author: Alexander Korablev (korablev43@mail.ru)
 * Date: 09.08.2014 17:04
 */
public enum  SchemaType {
    XSD {
        private final Validator validator = new XsdValidator();
        @Override
        public Validator getValidator() {
            return validator;
        }
    },
    DTD {
        private final Validator validator = new DtdValidator();
        @Override
        public Validator getValidator() {
            return validator;
        }
    };

    public abstract Validator getValidator();
}
