package com.gordon.strategy;

import com.gordon.adapter.Country;

public class CountryStrategy implements IntlStrategy {
  private final Country country;

  public CountryStrategy(Country country) {
    this.country = country;
  }

  @Override
  public String getName() {
    return country.name;
  }
}
