package br.com.caelum.agenda;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.com.caelum.agenda.model.Prova;

public class ProvasActivity extends AppCompatActivity {

    private ListView listaProvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.frame_principal, new ListaProvasFragment());
        if (estaNoModoPaisagem()) {
            tx.replace(R.id.frame_secundario, new DetalhesProvaFragment());
        }
        tx.commit();
    }

    public void selecionaProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();
        if (!estaNoModoPaisagem()) {
            FragmentTransaction tx = manager.beginTransaction();

            DetalhesProvaFragment detalhesFragment = new DetalhesProvaFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("prova", prova);
            detalhesFragment.setArguments(parametros);

            tx.replace(R.id.frame_principal, detalhesFragment);

            tx.addToBackStack(null);

            tx.commit();
        } else {
            DetalhesProvaFragment detalhesFragment = (DetalhesProvaFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesFragment.populaCamposCom(prova);
        }
    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }
}
