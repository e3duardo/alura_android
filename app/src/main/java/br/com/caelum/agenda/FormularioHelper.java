package br.com.caelum.agenda;

import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.agenda.model.Aluno;

/**
 * Created by eduardo on 29/12/16.
 */

public class FormularioHelper {
    private final FormularioActivity activity;
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;
        this.aluno = new Aluno();
        this.campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        this.campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
    }

    public Aluno getAluno() {
        this.aluno.setNome(campoNome.getText().toString());
        this.aluno.setEndereco(campoEndereco.getText().toString());
        this.aluno.setTelefone(campoTelefone.getText().toString());
        this.aluno.setSite(campoSite.getText().toString());
        this.aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return this.aluno;
    }

    public void preencherFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
