package Library.Library.Service;

import Library.Library.Entity.Livro;
import Library.Library.Repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService   {


    @Autowired
    LivroRepository repository;

    @Transactional
    public Livro save(Livro livro) {
        return repository.save(livro);
    }
}
