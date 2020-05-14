package ideam.sanlam.dv.application.internal.pipeline;

import ideam.sanlam.dv.application.internal.pipeline.actions.Pipe;
import ideam.sanlam.dv.common.exception.NoFilesReceivedError;

import java.util.ArrayList;
import java.util.List;

public class GenerationPipeline<T> {

    private List<Pipe<T>> pipelineSteps = new ArrayList<>();
    private T firstStepInput;

    public GenerationPipeline(T firstStepInput)
    {
        this.firstStepInput = firstStepInput;
    }

    public void addStep(Pipe action) {
        pipelineSteps.add(action);
    }

    public void process() {
        for (Pipe<T> action : pipelineSteps) {
            T out = action.process(firstStepInput);
            firstStepInput =  out;
        }
    }

}
