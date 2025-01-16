package br.paulos3r.reflection;

import br.paulos3r.reflection.refl.Transformator;

import java.lang.reflect.InvocationTargetException;

public class PessoaService {
  public PessoaDTO list() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Pessoa pessoa = new PessoaRepository().list();
    PessoaDTO pessoaDTO = new Transformator().trasform(pessoa);
    //PessoaDTO pessoaDTO = new PessoaDTO(pessoa.getNome(), pessoa.getCpf());
    return pessoaDTO;
  }
}
