package br.com.caelum.agenda.task;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.agenda.converter.AlunoConverter;
import br.com.caelum.agenda.dao.AlunoDao;
import br.com.caelum.agenda.model.Aluno;
import br.com.caelum.agenda.web.WebClient;

/**
 * Created by eduardo on 31/12/16.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando alunos...", true, true);
    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunos = dao.getAll();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}
