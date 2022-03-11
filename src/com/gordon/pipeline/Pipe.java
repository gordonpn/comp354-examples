package com.gordon.pipeline;

import com.gordon.pipeline.filter.Filter;

/**
 * This class serves to connect two filters together through a pipe.
 *
 * @param <I> The input type of the previous filter
 * @param <M> The output type of the previous filter as well as the input of the next filter
 * @param <O> The output type of the next filter
 */
public class Pipe<I, M, O> implements Filter<I, O> {
  Filter<I, M> previous;
  Filter<M, O> next;

  public Pipe(Filter<I, M> previous, Filter<M, O> next) {
    this.previous = previous;
    this.next = next;
  }

  /**
   * This passes the input through the previous filter, takes the output and puts it through the
   * next filter.
   *
   * @param input input of the previous filter
   * @return the output of the next filter
   */
  @Override
  public O process(I input) {
    return next.process(previous.process(input));
  }
}
