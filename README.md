#Booking movie ticket

REST application

### Reservation REST testing
Sample payload for `POST http://localhost:8080/reservations`

```json
{
  "startDate" : "1125-12-22T11:20:02",
  "duration" : "00:30:00",
  "employee" : "http://localhost:8080/customer/1",
  "room" : "http://localhost:8080/movie/1"
}
```

and the response is 201 Created

```json
{
  "id": 1,
  "startDate": "1125-12-22T11:20:02",
  "duration": "00:30:00",
  "submissionDate": "2017-02-27T00:19:21.002",
  "endDate": "1125-12-22T11:50:02",
  "links": [
    {
      "rel": "self",
      "href": "http://localhost:8080/reservations/1"
    },
    {
      "rel": "movie",
      "href": "http://localhost:8080/movie/1"
    },
    {
      "rel": "customer",
      "href": "http://localhost:8080/customers/1"
    }
  ]
}
```
