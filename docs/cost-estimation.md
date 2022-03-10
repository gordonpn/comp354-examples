# Cost Estimation Methods

## Estimation by Analogy

- Applicable if there exists projects in the same domain
- Estimating based on experience with previous project
- Rarely is the exact same project

## Expert Judgement

- Using knowledge of those with more experience
- Can tell the difference between previous projects and current project
- Can have biases and incorrect details about previous project from memory

## Algorithmic Model

- Uses math to estimate
- Three well known formulas: CoCoMo, SLIM and Function Points
- Calibrated using several factors (from past)
- But choosing a factor is subjective

### CoCoMo

- Formula: `a * size^b * c`
- Factors `a` and `b` based on type of project (organic, embedded, semi-detached)
- `size` is measured in thousands lines of code
- `c` is the adjustment factor based on 15 other factors (see lectures notes for table)

## Parkinson's Law

- Cost is determined by available resources instead of objective assessment
- Tends to be inaccurate, could be an overestimate or an underestimate
- In the case of an underestimation, it would lead to poor software

## Price to Win

- Guesstimates the price that the client is willing to pay for the project
- Functionality and quality of the software product is basically low priority

## Top-down Estimation

- A cost estimation (using a different method) is given at the top-most level then split among components/subprojects
- Requires more experience to determine where to allocate more cost

## Bottom-up Estimation

- A cost estimation is done for each component then summed together for the overall cost estimation
- System level costs may be easily neglected (DevOps and QA)
- More detailed/accurate as there is a responsible person for each component
