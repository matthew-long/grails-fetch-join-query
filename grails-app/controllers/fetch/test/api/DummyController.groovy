package fetch.test.api

import org.hibernate.LazyInitializationException

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DummyController {
	static responseFormats = ['json', 'xml']

    ExecutorService executorService = Executors.newSingleThreadExecutor()
	
    def index() {
        List<Book> books = []
        Book.list([fetch: [author: 'join']]).each {
            books.add(it)
        }
        try {
            println "Same Thread Author = ${books.author.name.toSet()}"
        } catch (LazyInitializationException ex) {
            println "Lazy Init Exception on Same Thread"
        }

        executorService.execute(new Runnable() {
            @Override
            void run() {
                List<Book> bookList = []
                Book.withTransaction {
                    // this join fetch doesn't seem to work
                    Book.list([fetch: [author: 'join']]).each {
                        bookList.add(it)
                    }
                }
                try {
                    println "Other Thread Author = ${bookList.author.name.toSet()}"
                } catch (LazyInitializationException ex) {
                    println "Lazy Init Exception on Other Thread"
                }
            }
        })

        respond books
    }
}
