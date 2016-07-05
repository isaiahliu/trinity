package org.trinity.example.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.example.common.message.exception.ErrorMessage;

@Component
public class ExampleProcessController implements IExampleProcessController {
    @Autowired
    private IExceptionFactory exceptionFactory;

    @Override
    public String process(final String process) throws IException {
        if (process == null) {
            throw exceptionFactory.createException(ErrorMessage.EMPTY_REQUEST);
        }
        return new StringBuilder(process).reverse().toString();
    }
}
