# Retail Product & Inventory API

A small, deliberately readable Spring Boot service used as the demo project throughout
my Eclipse IDE courses — building, navigating, refactoring, running and debugging real
Java code rather than toy examples.

## Learn Eclipse properly

Found this from one of my videos? Those cover one feature at a time. The courses cover
the whole IDE, in order, hands-on against this project.

### 📗 [Start free — Eclipse IDE for Java Developers: Getting Started](https://learningfromexperience.trainercentralsite.com/course/eclipse-ide-for-java-developers-getting-started)

Set up a workspace properly, write Java far faster than you're typing it now, refactor
without breaking things, and debug your first program instead of adding print
statements.

→ **[Enrol free](https://learningfromexperience.trainercentralsite.com/course/eclipse-ide-for-java-developers-getting-started)**

### 📙 [Go further — Eclipse IDE for Java Developers: Beginner to Advanced](https://learningfromexperience.trainercentralsite.com/course/eclipse-ide-for-java-developers-beginner-to-advanced)

Everything above, plus **Maven** when the build breaks, the full **Git and GitHub**
workflow including pull requests, **Spring Boot with Spring Tools**, and the debugging
most developers never learn — conditional breakpoints, exception breakpoints,
tracepoints, Hot Code Replace, and attaching to an application already running on
another machine.

→ **[Enrol in the full course](https://learningfromexperience.trainercentralsite.com/course/eclipse-ide-for-java-developers-beginner-to-advanced)**

---

## Quick start

Requires **JDK 21 or later** and Git. Maven is included via the wrapper.

```bash
git clone https://github.com/j2eeexpert2015/eclipse-spring-boot-retail-api.git
cd eclipse-spring-boot-retail-api
./mvnw spring-boot:run
```

The API starts on **http://localhost:8080** using an in-memory H2 database, seeded with
sample data. Nothing else to install.

Verify it's up:

```bash
curl http://localhost:8080/api/products
```

---

## Project structure

```
src/main/java/…
├── controller/     REST endpoints
├── service/        business logic — the layer most lessons debug through
├── repository/     Spring Data JPA repositories
├── model/          JPA entities
└── dto/            request and response records

src/main/resources/
├── application.properties
└── data.sql        sample data loaded at startup

src/test/java/…     unit and integration tests
```

The domain is intentionally small — products and stock levels — so that no lesson is
ever about understanding the business logic. It exists to be navigated, refactored,
broken and debugged.

---

## API endpoints

| Method | Path | Description |
|---|---|---|
| `GET` | `/api/products` | List all products |
| `GET` | `/api/products/{id}` | Fetch one product |
| `POST` | `/api/products` | Create a product |
| `PUT` | `/api/products/{id}` | Update a product |
| `DELETE` | `/api/products/{id}` | Delete a product |
| `GET` | `/api/inventory/low-stock` | Products below the reorder threshold |

H2 console while the app is running: **http://localhost:8080/h2-console**

---

## Open in Eclipse

1. **File → Import → Maven → Existing Maven Projects**
2. Select the cloned folder and click **Finish**
3. Wait for Maven to resolve dependencies — the progress bar is bottom-right
4. Right-click the project → **Run As → Spring Boot App**

If you have **Spring Tools** installed, the project appears in the **Boot Dashboard**
(Window → Show View → Other → Spring → Boot Dashboard), where you can start, stop,
restart and debug it, and inspect live request mappings and beans.

### If the project shows errors after import

Most import problems are Maven problems, and nearly all of them are fixed by:

**Right-click the project → Maven → Update Project → tick "Force Update of
Snapshots/Releases" → OK**

If that doesn't clear it, check that your installed JDK is **Java 21 or later**
(Window → Preferences → Java → Installed JREs).

---

## Running the tests

```bash
./mvnw test          # unit tests
./mvnw verify        # includes Testcontainers integration tests — Docker must be running
```

---

## Author

**Ayan Dutta** — independent technical content creator. Twenty years building Java
systems, now teaching the tools around them.

- **All courses** — [learningfromexperience.org/courses](https://learningfromexperience.org/courses/)
- **YouTube** — [@LearningFromExperience](https://www.youtube.com/@LearningFromExperience)
- **Medium** — [@mrayandutta](https://medium.com/@mrayandutta)
- **Udemy** — [udemy.com/user/ayandutta](https://www.udemy.com/user/ayandutta/)
- **Website** — [learningfromexperience.org](https://learningfromexperience.org)

Questions about the project or the courses: open an
[issue](https://github.com/j2eeexpert2015/eclipse-spring-boot-retail-api/issues) or
email **j2eeexpert2015@gmail.com**.

If this project or the courses were useful, a ⭐ on the repository helps other
developers find them.
