package fetch.test.api

import java.time.Instant

class Book {

    Author author
    String title

    Instant dateCreated
    Instant lastUpdated

    static constraints = {
        author nullable: false
        title nullable: false, blank: false, size: 1..100
    }

    static mapping = {
        // uncomment this and it works
//        author lazy: false
    }
}
