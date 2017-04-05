#Booking movie ticket

REST application. Technologies: Spring Boot, Spring JPA, PostgreSQL

### Customer REST testing
Sample payload for `POST http://localhost:8080/customers`

```json
{
  "firstName" : "John",
  "lastName" : "Smith",
  "telephone" : "+02938451345",
  "email" : "John@mail.com"
}
```

and the response is

```bash
201 CREATED
{
  "id": 1,
  "firstName": "John",
  "lastName": "Smith",
  "telephone": "+02938451345",
  "email": "John@mail.com",
  "content": [],
  "links": [
    {
      "rel": "self",
      "href": "http://localhost:8080/customers/1"
    },
    {
      "rel": "customer",
      "href": "http://localhost:8080/customers/1"
    }
  ]
}
```
There is possible validation error with *400 Bad request* response. Let's attempt to create customer without `firstName`:

```
POST localhost:8080/customers
{
  "lastName" : "Smith",
  "telephone" : "+02938451345",
  "email" : "John@mail.com"
}
```

```
400 BAD REQUEST
{
  "error": [
    "Customer's first name cannot be null"
  ]
}
```

### Movie REST testing

Sample payload for `POST http://localhost:8080/movies`

```json
{
  "name" : "Movie",
  "code" : "1",
  "startTime" : "09:00:00",
  "endTime" : "12:00:00"
}
```
and the response is 201 Created

```json
{
  "id": 1,
  "name": "Movie",
  "code": "1",
  "startTime": "09:00:00",
  "endTime": "12:00:00",
  "content": [],
  "links": [
    {
      "rel": "self",
      "href": "http://localhost:8080/movie/1"
    },
    {
      "rel": "movie",
      "href": "http://localhost:8080/movie/1"
    }
  ]
}
```

### Reservation REST testing
Sample payload for `POST http://localhost:8080/reservations`

```json
{
  "startDate" : "1125-12-22T11:20:02",
  "duration" : "00:30:00",
  "customer" : "http://localhost:8080/customer/1",
  "movie" : "http://localhost:8080/movie/1"
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
