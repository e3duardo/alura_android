package br.com.caelum.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.agenda.model.Aluno;
import br.com.caelum.agenda.util.Retorno;

/**
 * Created by eduardo on 29/12/16.
 */

public class AlunoDao extends SQLiteOpenHelper {
    public AlunoDao(Context context) {
        super(context, "agenda_database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL, caminhoFoto TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql ="";
        switch (oldVersion) {
            case 2:
                sql = "ALTER TABLE alunos ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql);
                break;
        }
    }

    public Retorno<Boolean, String> merge(Aluno aluno){
        if(aluno.getNome().isEmpty())
            return new Retorno(false,"Preencha o nome");

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());

        if(aluno.getId() == null) {
            if(db.insert("alunos", null, dados) > 0){
                return new Retorno(true,"Aluno " + aluno.getNome() + " adicionado!");
            }
        }else{
            String[] params ={aluno.getId().toString()};
            if(db.update("alunos", dados, "id = ?", params)>0){
                return new Retorno(true,"Aluno " + aluno.getNome() + " alterado!");
            }
        }
        return new Retorno(false,"Algo inesperado aconteceu!");
    }

    public List<Aluno> getAll() {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM alunos;";
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public boolean isAluno(String telefone) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT + FROM alunos WHERE telefone = ?", new String[]{telefone});
        int resultados = c.getCount();
        c.close();
        return resultados > 0;
    }

    public void delete(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {aluno.getId().toString()};
        db.delete("alunos", "id = ?", params);
    }
}
