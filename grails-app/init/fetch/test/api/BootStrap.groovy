package fetch.test.api

class BootStrap {

    def init = { servletContext ->
        Author author = new Author(name: "Some Guy").save()
        new Book(author: author, title: "Some Title").save()
        new Book(author: author, title: "Some Title 2: Electric Boogaloo").save()
    }
    def destroy = {
    }
}
