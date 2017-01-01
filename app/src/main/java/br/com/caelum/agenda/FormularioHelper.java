package br.com.caelum.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    private final ImageView campoFoto;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        this.activity = activity;
        this.aluno = new Aluno();
        this.campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        this.campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
    }

    public Aluno getAluno() {
        String caminhoFoto = null;
        if(campoFoto.getTag()!=null)
            caminhoFoto = campoFoto.getTag().toString();

        this.aluno.setNome(campoNome.getText().toString());
        this.aluno.setEndereco(campoEndereco.getText().toString());
        this.aluno.setTelefone(campoTelefone.getText().toString());
        this.aluno.setSite(campoSite.getText().toString());
        this.aluno.setNota(Double.valueOf(campoNota.getProgress()));
        this.aluno.setCaminhoFoto(caminhoFoto);

        return this.aluno;
    }

    public void preencherFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());
        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
