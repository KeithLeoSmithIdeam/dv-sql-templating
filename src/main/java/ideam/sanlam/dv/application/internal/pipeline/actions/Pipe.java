package ideam.sanlam.dv.application.internal.pipeline.actions;

import ideam.sanlam.dv.common.exception.NoFilesReceivedError;

public interface Pipe<T> {
    T process (T input) throws NoFilesReceivedError;
}
