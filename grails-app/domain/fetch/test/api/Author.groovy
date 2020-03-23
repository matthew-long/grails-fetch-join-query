package fetch.test.api

import java.time.Instant

class Author {

    String name

    Instant dateCreated
    Instant lastUpdated

    static constraints = {
        name nullable: false, blank: false, size: 1..100
    }
}
