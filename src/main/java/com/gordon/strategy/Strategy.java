package com.gordon.strategy;

/**
 * Parent interface of interface NorthAmericanStrategy and interface IntlStrategy because we don't
 * to force both NorthAmericanStrategy and IntlStrategy to implement methods they don't need
 * (interface segregation)
 */
public interface Strategy {}
