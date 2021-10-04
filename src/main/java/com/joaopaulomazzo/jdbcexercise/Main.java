package com.joaopaulomazzo.jdbcexercise;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        CursoDAO cursoDAO = new CursoDAO();

//------------------consulta-------------------//
        List<Curso> cursos = cursoDAO.list();
        cursos.forEach(System.out::println);

//----------consulta com filtro de id----------//
        Curso cursoParaConsulta = cursoDAO.getById(2);
//        System.out.println(cursoParaConsulta);

//-------------insere um novo dado-------------//
        Curso cursoParaInserir = new Curso(
                "Java",
                30
        );
//        cursoDAO.create(cursoParaInserir);

//---------------remove um dado---------------//
//        cursoDAO.delete(4);

//--------------atualiza um dado--------------//
        Curso cursoParaAtualizar = cursoDAO.getById(2);
        cursoParaAtualizar.setNome("C#");
        cursoParaAtualizar.setDuracaoHoras(25.5F);

//        cursoDAO.update(cursoParaAtualizar);
    }
}
