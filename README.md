# bank-account-manager

This is a simple application to mimic two action (GetBalance and TransferMoney)

1. /v1/account/{accountNumber} for getting balance
2. /v1/account/transfer for transfer money between client

Since the application is conducting money transfer, transaction would be used in this scenario.
But for now, we are using h2 default isolation level which is read committed which is enough for this simple application.

Possible Improvements:
1. More verbal message telling frontend or user of what goes wrong, instead of merely an error code
2. Possibly adding some security, for example user authentication by adding filter to security chain.