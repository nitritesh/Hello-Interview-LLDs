# Low Level Design Problems (Java 11)

Java implementations of the classic LLD interview problems from
[hellointerview.com](https://www.hellointerview.com/learn/low-level-design/in-a-hurry/introduction).

Each problem lives in its own package under `com.lld` and ships with a runnable
`*Demo` class containing a `main` method that demonstrates the core behaviour.
The project is a plain Maven project with **no external dependencies**, so it
opens and runs in IntelliJ offline.

## Requirements

- Java 11+ (compiles with `maven.compiler.source/target = 11`)
- Optional: Maven, if you want to build from the command line

## Opening in IntelliJ

1. `File > Open` and select the `lld-problems` folder (the one with `pom.xml`).
2. IntelliJ detects the Maven project and imports it.
3. Set the Project SDK to Java 11 (or newer) if prompted.
4. Run any `*Demo` class, or run `com.lld.RunAll` to execute all of them.

## Running from the command line

Run everything:

```bash
mvn -q compile exec:java -Dexec.mainClass=com.lld.RunAll
```

(If you don't use the exec plugin, just compile with `mvn compile` and run a
class from your IDE.)

## Problems

| # | Problem | Package | Demo | Key ideas |
|---|---------|---------|------|-----------|
| 1 | Connect Four | `com.lld.connectfour` | `ConnectFourDemo` | 2D board, win detection in 4 directions |
| 2 | Amazon Locker | `com.lld.amazonlocker` | `AmazonLockerDemo` | Best-fit assignment, one-time pickup codes |
| 3 | Elevator | `com.lld.elevator` | `ElevatorDemo` | SCAN algorithm, nearest-car dispatch |
| 4 | Parking Lot | `com.lld.parkinglot` | `ParkingLotDemo` | Vehicle/spot sizing, tickets, levels |
| 5 | File System | `com.lld.filesystem` | `FileSystemDemo` | Composite pattern, recursive size + search |
| 6 | Movie Ticket Booking | `com.lld.movieticket` | `MovieTicketDemo` | Atomic seat locking, no double-booking |
| 7 | Logging Service | `com.lld.logging` | `LoggingDemo` | Level thresholds, pluggable appenders |
| 8 | Rate Limiter | `com.lld.ratelimiter` | `RateLimiterDemo` | Token bucket, sliding + fixed window |
| 9 | Inventory Management | `com.lld.inventory` | `InventoryDemo` | Observer pattern, low-stock alerts |

## Design notes

These implementations favour clarity over completeness — they show the core
class model and one or two interesting mechanics per problem (the parts
interviewers usually probe), not every edge case a production system needs.
Common threads:

- **Enums for bounded types** (sizes, levels, directions, statuses).
- **Strategy pattern** for swappable behaviour (rate limiter algorithms, log appenders).
- **Composite pattern** for tree structures (file system).
- **Observer pattern** for decoupled reactions (inventory low-stock alerts).
- **Synchronized critical sections** where concurrency matters (seat booking, token buckets).
