package com.gordon.pipeline;

import com.gordon.pipeline.filter.Filter;

/**
 * A pipeline object that represents all the filters and pipes connecting them
 *
 * @param <I> input type of the pipeline
 * @param <O> output type of the pipeline
 */
public class Pipeline<I, O> {
  Filter<I, O> current;

  public Pipeline(Filter<I, O> current) {
    this.current = current;
  }

  /**
   * This method is used to connect more filters to an existing pipeline object.
   *
   * @param next The next filter to connect
   * @param <M> The output type of the next filter, which may be different from the current filter
   * @return a new pipeline object with the filters connected
   */
  public <M> Pipeline<I, M> addPipe(Filter<O, M> next) {
    return new Pipeline<>(new Pipe<>(current, next));
  }

  public O process(I input) {
    return current.process(input);
  }
}
