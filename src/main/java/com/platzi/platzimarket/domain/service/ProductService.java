package com.platzi.platzimarket.domain.service;

        import com.platzi.platzimarket.domain.Product;
        import com.platzi.platzimarket.persistence.ProductoRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Product> getAll(){
        return productoRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productoRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productoRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productoRepository.save(product);
    }

    public boolean delete(int productId){
        productoRepository.delete(productId);
        return getProduct(productId).map(product -> {
            productoRepository.delete(productId);
            return true;
        }).orElse(false);

        /**Otra forma
            if(getProduct(productId).isPresent()){
                productoRepository.delete(productId);
                return true;
            }else{
               return false;
            }*/
    }
}