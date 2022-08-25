package ru.natology.Task141;


public class Repository {
    public Product[] products = new Product[0];

    public void add(Product product) {
        for (Product thisProduct : products){
            if (product.getId() == thisProduct.getId()){
                throw new AlreadyExistsException("Product с таким id уже существует" + product.getId());
            }

        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public void removeById(int id) {


        if (findById(id) == null){
            throw new NotFoundException("Product с таким id нет" + id);
        }
        Product[] tmp = new Product[products.length - 1];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[index] = product;
                index++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {

        // Product result = null;
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
                //  break;
            }
        }
        return null;
    }

    public Product[] findAll() {
        return products;
    }
}

