## Sample requests and responses

### Register user
```
curl --request POST \
  --url 'http://localhost:8080/user/v1/register?username=Vishnu'
  
```

Response: `User Vishnu registered successfully.`

### Fetch wallet balance
```
curl --request POST \
  --url 'http://localhost:8080/wallet/v1/fetchBalance?username=Vishnu'
```

Response: `Vishnu's wallet has Rs.20`

### TopUp wallet
```
curl --request POST \
  --url http://localhost:8080/transaction/v1/topUp \
  --header 'Content-Type: application/json' \
  --data '{
	"username": "Vishnu",
	"amount": 100,
	"paymentMode": "UPI"
}'
```

Response: `Vishnu credited with Rs.100 via UPI`

### Send money
```
curl --request POST \
  --url http://localhost:8080/transaction/v1/sendMoney \
  --header 'Content-Type: application/json' \
  --data '{
	"senderName": "Neha",
	"receiverName": "Vishnu",
	"amount": 20
}'
```
Response: `Neha has transferred Rs.20 to Vishnu`

### Get transactions
```
curl --request GET \
  --url 'http://localhost:8080/transaction/v1/getTransactions?username=Neha&filter=SEND&sorter=AMOUNT'
```
Response: 
```json
[
	{
		"amount": 200,
		"transactionType": "WALLET_TO_WALLET_TRANSFER",
		"timestamp": "2026-10-16T00:42:15.235707"
	},
	{
		"amount": 200,
		"transactionType": "WALLET_TO_WALLET_TRANSFER",
		"timestamp": "2026-10-16T00:42:17.315184"
	}
]
```