package org.trinity.example.process;

import org.trinity.common.exception.IException;

public interface IExampleProcessController {
    String process(String process) throws IException;
}
