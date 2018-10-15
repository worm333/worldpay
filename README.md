# worldpay
### Table of Contents 
1. [About](https://github.com/worm333/worldpay#about)
2. [How to build and run](https://github.com/worm333/worldpay#how-to-build-and-run)
3. [How to use](https://github.com/worm333/worldpay#how-to-use)


# About
This project was build using Spring Boot framework and H2 in memory database.

# How to build and run

```
git clone https://github.com/worm333/worldpay.git
cd worldpay/
sudo mvn clean package
java -jar target/merchant-offer-0.0.1-SNAPSHOT.jar
```
# How to use
1. Create offer: `curl -H "Content-Type: application/json" -X POST --data '{"name":"xyz","description":"xyz", "expiration":"2018-10-30 06:35:40.622", "price":"100", "currency":"USD"}' http://localhost:8080/offer`

2. View offer: `curl http://localhost:8080/offer/{id}`

3. Cancel offer: `curl -X POST http://localhost:8080/offer/{id}/cancel`

4. List all offers: `curl http://localhost:8080/offer`

5. Update offer: `curl -H "Content-Type: application/json" -X PUT --data '{"name":"xyz","description":"xyz", "expiration":"2018-10-30 06:35:40.622", "price":"100", "currency":"USD"}' http://localhost:8080/offer/{id}`

6. Delete offer: `curl -X DELETE http://localhost:8080/offer/{id}`

7. Search for offers: curl `http://localhost:8080/offer/search/query?name=a&description=b`
Search could be performed for all fileds and each field is optional:
-name
-description
-price
-currency
-canceled
-beforeDate - before expiration date
-afterDate - after expiration date

In order to perform search by date the following date format `yyyy-MM-dd HH:mm:ss.SSS` MUST be used, e.g:`/offer/search/query?beforeDate=2018-10-30 06:35:40.622`.


Note. When user tries to view canceled offer: `curl http://localhost:8080/offer/1` server returns status 410 (Gone).
