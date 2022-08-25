package ru.natology.Task141;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ProductManagerTest {


    Repository repo = new Repository();
    ProductManager manager = new ProductManager(repo);
    Book book1 = new Book(1, "book 1", 320, "Author 1");
    Book book2 = new Book(22, "book 2", 220, "Author 2");
    Book book3 = new Book(32, "magazine", 3203, "Author 3");
    Smartphone smartphone1 = new Smartphone(10, "smartphone 1", 1000, "manufacturer 1");
    Smartphone smartphone2 = new Smartphone(23, "smartphone 2", 2000, "manufacturer 2");
    Smartphone smartphone3 = new Smartphone(33, "smartphone 3", 2500, "manufacturer 3");

    @Test
    public void addTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("book");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void searchForTheMissingTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        Product[] expected = {};
        Product[] actual = manager.searchBy("newspaper");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void textSearchTest() {
        manager.add(book3);
        manager.add(book2);
        manager.add(book1);

        Product[] expected = {book2};
        Product[] actual = manager.searchBy("book 2");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeDyIdTest() {
        Repository repo = new Repository();

        repo.add(book3);
        repo.add(book2);
        repo.add(book1);
        repo.removeById(book2.getId());

        Product[] expected = {book3, book1};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);


    }


    @Test
    public void addSmartphoneTest() {
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {smartphone1, smartphone2, smartphone3};
        Product[] actual = manager.searchBy("smartphone");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void removingNonExistentElementTest() {
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Assertions.assertThrows(NotFoundException.class,
                () -> repo.removeById(6));
    }

    @Test
    public void removeDyIdSmartphone() {
        Repository repo = new Repository();

        repo.add(smartphone1);
        repo.add(smartphone2);
        repo.add(smartphone3);
        repo.removeById(smartphone2.getId());

        Product[] expected = {smartphone1, smartphone3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }
    @Test
    public void addingAnExistingProductTest() {
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Assertions.assertThrows(AlreadyExistsException.class,
                () -> repo.add(smartphone1));
    }
    @Test
    public void addProductTest() {
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {smartphone1, smartphone2, smartphone3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }


}
