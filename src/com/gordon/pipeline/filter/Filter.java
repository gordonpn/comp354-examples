package com.gordon.pipeline.filter;

/**
 * This interface can be used to create more filters
 *
 * @param <I> input type of the filter
 * @param <O> output type of the filter
 */
public interface Filter<I, O> {
  O process(I input);
}
