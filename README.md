# Distributed Order-Payment Microservices System

- Designed and implemented an event-driven microservices architecture using Spring Boot and Apache Kafka to handle order and payment workflows asynchronously.

- Built Order Service (Producer) and Payment Service (Consumer) using Kafka topics (order-created, payment result events).

Implemented Saga-based workflow for order lifecycle management (Created → Payment Success/Failure → Cancelled).

Designed versioned event contracts to ensure loose coupling between services.

Added idempotent event processing to handle duplicate events and consumer restarts safely.

Implemented failure handling mechanisms including payment timeouts, retries, and duplicate-event protection.

Developed scheduled background jobs (@Scheduled) to:

Auto-cancel unpaid orders

Retry failed payments

Clean up stale data

Ensured eventual consistency and reliability in a distributed environment.

Tech Stack: Java, Spring Boot, Apache Kafka, JPA/Hibernate, MySQL, Scheduled Jobs
