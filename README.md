# Loan Quotation Calculator CLI
## Pre-requisites:
- Java 11
- Maven

## Build
```mvn clean install```

## Run
You can run the application using a pre-built jar in the folder ```/build```

```java -jar build/quote.jar data/market_data.csv 1000```

###Usage
```
▶ java -jar build/quote.jar --help

Usage: java -jar quote.jar [-hV] [-t=<termInMonths>] [FILE] [LOAN_AMOUNT]
      [FILE]          The Input CSV file containing a list of all the offers made by
                        lenders
      [LOAN_AMOUNT]   The loan amount requested by a Borrower, minimum £1,000 and
                        maximum £15,000 inclusive and in increments of 100's
  -h, --help          Show this help message and exit.
  -t, --term=<termInMonths>
                      An option to change the repayment terms in months, default
                        value = 36
  -V, --version       Print version information and exit.
```
   
# Dependencies
 
 - picocli - https://picocli.info/ CLI interface, param parsing