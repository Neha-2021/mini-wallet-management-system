# Design a Mini Wallet

Design a prepaid mini wallet an E-commerce application through which users can send and receive money among
themselves and can get cashback in wallet. The user can load money into the wallet via various modes of
sources like Credit Card, Debit Card or UPI etc and perform easy transactions.

## Requirements:

* Users need to register on Flipkart to use this wallet. 

* The user can load money into his wallet via various sources (Credit Card, Debit Card, UPI etc). 
  * Minimum amount of load money should be greater than 0. 
  * Assumption - No need for Inte*gration from sources, can be implemented just success
    acknowledged. 
* The user can send money to other users from their wallet. 
  * The minimum amount of transaction will be always greater the 0.
  * The user must have a sufficient balance in his wallet while doing the transaction.
* The user can fetch their wallet balance at any point of time, this should consider both credit & debit type of transactions. 
* The user can get transaction history based on 
  * Sort their all transactions history 
    * Based on Amount (“amount”)
    * Based on Transaction DateTime (“time”)
  * Filter transactions history 
    * Based on Send or Receive Amount (“send/receive”)
  * Assumption: Consider single filter & sorted field
            
* BONUS 
  * The transactions will be eligible for cashback if they meet their respective criteria. Let's say criteria can be, based on “after each 5 transactions” or “With 0.5 probability for each transaction”, provide X amount as cashback and added to the user's wallet.

## Capabilities:

Below are various functions that should be supported with the necessary parameters passed.
These are just suggestions, the signatures can be altered as long as the functionalities are provided.

* registerUser() - Register user to use wallet 
* topUpWallet() - Add money to the wallet for users with any of the resources. 
* fetchBalance() - Fetch balance available in the wallet for user 
* sendMoney() - Send money to the user. Each money transfer will be considered as a transaction. 
* getTransactions(filter, sorter) - fetches transactions history based on filter and sorting criteria 
* getCashback() - Get total cashback amount for eligible transactions.

## Expectations:

1. Create the sample data yourself. You can put it into a file, test case or main driver program or via Unit tests itself.
2. Database integration is not required. You should store the data in memory using a language-specific data structure.
3. The code should be demo-able. Either by using the main driver program or test cases.
4. The code should be modular. The code should have the basic OO design. Please do not jam in the responsibilities of one class into another.
5. The code should be extensible. Wherever applicable, use interfaces and contracts between different methods. It should be easy to add/remove functionality without rewriting the entire codebase.
6. The code should handle edge cases properly and fail gracefully.
7. The code should be legible, readable and DRY .
