# EventHub

EventHub, etkinliklerin oluşturulması ve kullanıcıların bu etkinliklere bilet oluşturabilmesi amacıyla geliştirilmiş, **Java ve Spring Boot tabanlı bir mikroservis projesidir.**

Proje; kullanıcı kimlik doğrulama, etkinlik yönetimi, bilet oluşturma ve Kafka üzerinden bildirim servisine olay aktarımı gibi temel mikroservis konseptlerini uygulamak amacıyla geliştirilmiştir.

## Technologies

* Java 21
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA / Hibernate
* PostgreSQL
* Spring Cloud OpenFeign
* Apache Kafka
* Maven

## Architecture

EventHub, her biri kendi sorumluluğuna sahip bağımsız Spring Boot servislerinden oluşur.


                         ┌──────────────────┐
                         │ Identity Service  │
                         │    :8080          │
                         └────────┬─────────┘
                                  │
                               JWT
                                  │
                                  ▼
┌──────────────────┐       ┌──────────────────┐
│   Event Service  │◄──────│  Ticket Service  │
│      :8081       │ Feign │      :8082       │
└──────────────────┘       └────────┬─────────┘
                                    │
                              TicketCreated
                                    │
                                    ▼
                            ┌───────────────┐
                            │     Kafka     │
                            │    :9092      │
                            └───────┬───────┘
                                    │
                                    ▼
                         ┌────────────────────┐
                         │ Notification       │
                         │ Service :8083      │
                         └────────────────────┘
```

### Services

| Service              | Port | Responsibility                              |
| -------------------- | ---: | ------------------------------------------- |
| Identity Service     | 8080 | User registration, login and JWT generation |
| Event Service        | 8081 | Event CRUD operations                       |
| Ticket Service       | 8082 | Ticket creation and user's tickets          |
| Notification Service | 8083 | Consumes ticket events from Kafka           |

Each service has its own application configuration and database where persistence is required.



## Authentication Flow

Authentication işlemleri **Identity Service** tarafından gerçekleştirilir.

1. Kullanıcı Identity Service üzerinden kayıt olur.
2. Kullanıcı email ve password ile login olur.
3. Identity Service bilgileri doğrular ve bir JWT üretir.
4. JWT içerisinde kullanıcının `userId` bilgisi claim olarak tutulur.
5. Kullanıcı JWT ile Ticket Service'e istek gönderir.
6. Ticket Service JWT'yi doğrular ve `userId` bilgisini token içerisinden alır.
7. Kullanıcının kimliği request body üzerinden değil, JWT üzerinden belirlenir.


Client
  │
  │ Login (email + password)
  ▼
Identity Service
  │
  │ JWT
  ▼
Client
  │
  │ Authorization: Bearer <token>
  ▼
Ticket Service
  │
  │ Extract userId from JWT
  ▼
Create Ticket


Bu yapı sayesinde kullanıcı, başka bir kullanıcının `userId` değerini request body içerisinde göndererek onun adına bilet oluşturamaz.

## Kafka Event Flow

EventHub'da Kafka, Ticket Service ile Notification Service arasındaki asenkron iletişim için kullanılmaktadır.

Bir kullanıcı bilet oluşturduğunda aşağıdaki akış gerçekleşir:

1. Client, Ticket Service'e `POST /tickets` isteği gönderir.
2. Ticket Service JWT üzerinden kullanıcının `userId` bilgisini alır.
3. Event Service üzerinden gönderilen `eventId` değerinin geçerli olduğu kontrol edilir.
4. Ticket bilgisi Ticket Service'in PostgreSQL veritabanına kaydedilir.
5. Ticket Service, `TicketCreatedEvent` nesnesi oluşturur.
6. Event, Kafka'daki `ticket-created` topic'ine gönderilir.
7. Notification Service bu topic'i dinler.
8. Notification Service event'i consume eder ve şu anda mesajı konsola yazdırır.


Client
   │
   │ POST /tickets
   ▼
Ticket Service
   │
   ├── Save Ticket
   │
   └── TicketCreatedEvent
            │
            ▼
      Kafka Producer
            │
            │ ticket-created
            ▼
          Kafka
            │
            ▼
  Notification Service
            │
            └── Consume Event
```

### TicketCreatedEvent

Kafka üzerinden gönderilen event aşağıdaki bilgileri içerir:


{
  "ticketId": 2,
  "userId": 4,
  "eventId": 3
}


Notification Service şu anda bu event'i sadece consume ederek konsola yazdırmaktadır. Bu yapı ileride email, SMS veya başka bir notification mekanizması eklenebilecek şekilde temel bir event-driven communication örneği oluşturur.


## Kafka Event Flow

EventHub'da Kafka, Ticket Service ile Notification Service arasındaki asenkron iletişim için kullanılmaktadır.

Bir kullanıcı bilet oluşturduğunda aşağıdaki akış gerçekleşir:

1. Client, Ticket Service'e `POST /tickets` isteği gönderir.
2. Ticket Service JWT üzerinden kullanıcının `userId` bilgisini alır.
3. Event Service üzerinden gönderilen `eventId` değerinin geçerli olduğu kontrol edilir.
4. Ticket bilgisi Ticket Service'in PostgreSQL veritabanına kaydedilir.
5. Ticket Service, `TicketCreatedEvent` nesnesi oluşturur.
6. Event, Kafka'daki `ticket-created` topic'ine gönderilir.
7. Notification Service bu topic'i dinler.
8. Notification Service event'i consume eder ve şu anda mesajı konsola yazdırır.


Client
   │
   │ POST /tickets
   ▼
Ticket Service
   │
   ├── Save Ticket
   │
   └── TicketCreatedEvent
            │
            ▼
      Kafka Producer
            │
            │ ticket-created
            ▼
          Kafka
            │
            ▼
  Notification Service
            │
            └── Consume Event
```

### TicketCreatedEvent

Kafka üzerinden gönderilen event aşağıdaki bilgileri içerir:


{
  "ticketId": 2,
  "userId": 4,
  "eventId": 3
}


Notification Service şu anda bu event'i sadece consume ederek konsola yazdırmaktadır. Bu yapı ileride email, SMS veya başka bir notification mekanizması eklenebilecek şekilde temel bir event-driven communication örneği oluşturur.


